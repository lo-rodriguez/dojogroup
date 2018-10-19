/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.DISABLED;
import com.dojogrouppty.common.FileUtil;
import com.dojogrouppty.common.MODULES;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.VARS_SESSION;
import com.dojogrouppty.error.GenericBZKException;
import com.dojogrouppty.products.ProductsService;
import com.dojogrouppty.students.Student;
import com.dojogrouppty.students.StudentRepository;
import com.dojogrouppty.students.StudentService;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author lrodriguezn
 */
@Controller
public class PaymentsController extends ParentControllerService {

    @Autowired
    private ProductsService productsService;
    @Autowired
    private CatalogsService catalogsService;
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ReceiptPDFService createReceiptPDFService;
    private static final Logger logger
            = LoggerFactory.getLogger(PaymentsController.class);
    @Autowired
    MessageSource messageSource;
    /**
     * Method for display payment form
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/paymentStudent", method = RequestMethod.GET)
    String payment(Model model, @RequestParam(ID_STUDENT) int id, HttpSession httpSession) {
        try {
            PaymentForm form = new PaymentForm();
            httpSession.setAttribute(VARS_SESSION.ID_STUDENT.varOption(), id);
            model.addAttribute(PAYMENT_FORM_OBJ, form);
            model.addAttribute(DISABLED__, DISABLED.ENABLED.getValue());
            model.addAttribute(_MODULE, MODULES.PAYMENT.moduleOption());
            model.addAttribute(FORM_ACCION, ACCION_PAYMENT_STUDENT);
            model.addAttribute(PRODUCTS, productsService.getActiveProducts());
            model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
            model.addAttribute(PAY_DATE, DateFormat.getInstance().format(new Date()));
        } catch (Exception e) {
            logger.error("Error in payment GET..", e);
        }
        return PAGE_PAYMEN;
    }
    /**
     * Method for display payment form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/multiPayment", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public String multiPayment(Model model) {
        try {
            PaymentForm form = new PaymentForm();
            model.addAttribute(PAYMENT_FORM_OBJ, form);
            model.addAttribute(DISABLED__, DISABLED.ENABLED.getValue());
            model.addAttribute(_MODULE, MODULES.PAYMENT.moduleOption());
            model.addAttribute(FORM_ACCION, ACCION_PAYMENT_STUDENT);
            model.addAttribute(PRODUCTS, productsService.getActiveProducts());
            model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
            model.addAttribute(PAY_DATE, DateFormat.getInstance().format(new Date()));
            model.addAttribute(DESCPTION_STUDENTS,studentService.getDescriptionStudentDTO() );
        } catch (Exception e) {
            logger.error("Error in payment GET..", e);
        }
        return PAGE_MULTI_PAYMENT;
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/processPayments",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String processPayments(@RequestParam("data") String data) {
        logger.debug("Into processPayments.. ");
        logger.debug("data: " + data);
        String result = "";
        PaymentResponse paymentResp;
        try {
            PaymentDTO dto = (PaymentDTO) gson.fromJson(data, PaymentDTO.class);
            logger.debug("datePay: " + dto.getDatePay() + ", typePayment: " + dto.getTypePayment());
            for (DetailPaymentDTO det : dto.getDetails()) {
                logger.debug("IdProduct: " + det.getIdProduct());
                logger.debug("IdStudent: " + det.getIdStudent());
                logger.debug("Tax: " + det.getTax());
                logger.debug("Total: " + det.getTotal());
                logger.debug("Comment: " + det.getComment());
                logger.debug("-------------------------------");
            }
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            String userRegister = (String) session.getAttribute(VARS_SESSION.REGISTER_USER.varOption());
            if (userRegister == null) {
                logger.error("Error al obtener los datos de sesion ..");
                String message = messageSource.getMessage(MESSAGE_ERROR_SESSION, null, Locale.getDefault());         
                paymentResp = new PaymentResponse();
                paymentResp.setOk(NO_OK);
                paymentResp.setMessage(message);
                return gson.toJson(paymentResp, PaymentResponse.class);
            }
            paymentResp = paymentsService.savePayment(dto, userRegister);
           if(paymentResp!=null && paymentResp.getNumPayment()!=null && !"".equals(paymentResp.getNumPayment())){ 
             try{  
             createReceiptPDFService.createReceiptPDF(dto, paymentResp, userRegister);
             }catch( URISyntaxException|DocumentException|IOException  d){
                  logger.error("Error:[" + d.getMessage() + "] String[" + d.toString() + "]");
                  paymentResp.setOk(NO_OK_PDF);
             }         
           }  
           result = gson.toJson(paymentResp, PaymentResponse.class);
        } catch (JsonSyntaxException | IllegalStateException | NoSuchMessageException | GenericBZKException e) {
            logger.error("Error:[" + e.getMessage() + "] String[" + e.toString() + "]");
             paymentResp = new PaymentResponse();
             paymentResp.setOk(NO_OK);
             paymentResp.setMessage(e.getMessage());
             return gson.toJson(paymentResp, PaymentResponse.class);
        }
        return result;
    }

    /**
     * Method that keeps payments made
     *
     * @param model
     * @param paymentForm
     * @param errors
     * @param ra
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/paymentStudent", method = RequestMethod.POST)
    String payment(Model model, @Valid @ModelAttribute PaymentForm paymentForm, Errors errors, RedirectAttributes ra, HttpSession httpSession) {
        try {
            model.addAttribute(_MODULE, MODULES.PAYMENT.moduleOption());
            model.addAttribute(FORM_ACCION, ACCION_PAYMENT_STUDENT);
            model.addAttribute(PRODUCTS, productsService.getActiveProducts());
            model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
            model.addAttribute(PAY_DATE, DateFormat.getInstance().format(dateformat.parse(paymentForm.getDatePay())));
            if (errors.hasErrors()) {
                for (ObjectError err : errors.getAllErrors()) {
                    logger.debug("Error:" + err.getCode());
                }
                return PAGE_PAYMEN;
            }
            Integer id = (Integer) httpSession.getAttribute(VARS_SESSION.ID_STUDENT.varOption());
            String userRegister = (String) httpSession.getAttribute(VARS_SESSION.REGISTER_USER.varOption());
            if (userRegister == null || id == null) {
                logger.error("Error al obtener los datos de sesion ..");
                return ERROR_ACTION;
            } else {
                paymentForm.setIdStudent(id);
                paymentForm.setUserRegister(userRegister);
                
                PaymentForm lastPayment =  (PaymentForm) httpSession.getAttribute(VARS_SESSION.OBJECT_PAYMENT.varOption());
                List<String> listErr  = new ArrayList<String>();
                if(lastPayment!=null && lastPayment.equals(paymentForm) ){
                     String message = messageSource.getMessage(ERROR_DUPLICATE_PAYMENT, null, Locale.getDefault()); 
                     listErr.add(message);
                     model.addAttribute(LIST_ERR, listErr);
                     return PAGE_PAYMEN;
                }else{
                    httpSession.setAttribute(VARS_SESSION.OBJECT_PAYMENT.varOption(), paymentForm);
                }                
                listErr = paymentsService.validField(paymentForm);
                if (listErr.isEmpty()) {
                    paymentsService.savePayment(paymentForm);
                    model.addAttribute(OK_PAYMENT_STUDENT, OK);
                    String description = paymentsService.productPaymentDescription();
                    Student st = studentRepository.findOne((long) id);
                    String message = messageSource.getMessage(FINAL_MESSAGE_PAYMENT, null, Locale.getDefault()); 
                    message = message.replace("{firstName}", st.getFirstName()).replace("{productsName}", description);
                    model.addAttribute(GENERAL_MODAL_MESSAGE, message);
                    model.addAttribute(DISABLED__, DISABLED.DISABLED_OFF.getValue());
                } else {
                    model.addAttribute(LIST_ERR, listErr);
                }
            }
        } catch (ParseException | NoSuchMessageException | GenericBZKException e) {
            logger.error("Error in payment POST..", e);
        }

        return PAGE_PAYMEN;
    }
    /**
     * Get UNIT PRICE OF PRODUCT BY ID
     * @param id
     * @return FLOAT UNIT PRICE,AMOUNT PLAYABLE,FAX
     */
    @RequestMapping(value = "/getUnitPrice/{id}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public String valueTotalProduc(@PathVariable Long id
    ) {

        try {
            return paymentsService.amountPayableJson(id);
        } catch (GenericBZKException ex) {
            logger.error("Error in payment POST..", ex);
            return null;
        }
    }
     @RequestMapping(value = "downloadReceipt/{idReceipt}", method = RequestMethod.GET)
     @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public void downloadPdf(@PathVariable Long idReceipt, HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String pdfFilePath = "";
        logger.info("Downloading A .PDF File From The Server ....!");
        /**** Get The Absolute Path Of The File ****/
        pdfFilePath = new StringBuilder(LOCATION_PDF).append(File.separator).append(idReceipt).append(".").append(PDF).toString();      
        logger.info("Absolute Path Of The .PDF File Is?= " + pdfFilePath);
        File downloadFile = new File(pdfFilePath);
        if(downloadFile.exists()) {
            FileUtil.downloadFileProperties(req, resp, pdfFilePath, downloadFile);              
        } else {
            logger.info("Requested .PDF File Not Found At The Server ....!");
        }
    }
}
