/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import com.dojogrouppty.catalogs.SystemCodes;
import com.dojogrouppty.catalogs.SystemCodesRepository;
import com.dojogrouppty.common.ERROR_PAYMENTS;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.error.GenericBZKException;
import com.dojogrouppty.products.Products;
import com.dojogrouppty.products.ProductsService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PaymentsService extends ParentControllerService {

    @Autowired
    private PaymentsRepository paymentsRepository;
    @Autowired
    private ProductsService productsService;
    private static Products prod = null;
    @Autowired
    private SystemCodesRepository systemCodesRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    DetailPaymentRepository detailPaymentRepository;
    @Autowired
    NumberFormat currencyFormatter;
    private static final Logger logger
            = LoggerFactory.getLogger(PaymentsService.class);

    public int amountOfPaymentsMade() {
        logger.debug("Into of amountOfPaymentsMade..");
        return paymentsRepository.amountOfPaymentsMade();
    }

    /**
     * method for save payment
     *
     * @param form
     * @return entity payment with id of payment
     */
    @Transactional
    public Payments savePayment(PaymentForm form) {
        Payments payment = paymentsRepository.save(build(form));
        return payment;
    }

    /**
     * Method return build payment
     *
     * @param form
     * @return
     */
    private Payments build(PaymentForm form) {
        Payments payment = new Payments();
        payment.setDateRegistration(form.getDateRegister());
        payment.setNameOfBank(form.getNameBank());
        payment.setNumberOfTransfer(form.getNumberOfTransfer());
        payment.setPayday(new Date());
        payment.setTotalTax(form.getTotalTax());
        payment.setTotalPayment(form.getTotalPayment());
        payment.setTypePayment(Integer.parseInt(form.getTypePayment()));
        payment.setUserRegister(form.getUserRegister());
        return payment;
    }
    /**
     * Method that builds the payment
     * @param dto
     * @param userRegister
     * @param err
     * @return
     * @throws ParseException
     */
      private Payments build(PaymentDTO dto,String userRegister,Map <Integer,String>  err) throws ParseException {
        Payments payment = new Payments();        
        payment.setDateRegistration(new Date());
        if(dto.getDatePay()==null || dto.getDatePay().isEmpty()){
         err.put(0, messageSource.getMessage(ERROR_PAYMENTS.ERROR_DAY_PAYMENT.getValue(), null, Locale.getDefault()))   ;                
         return null;
        }
        if(dto.getTypePayment()==null || dto.getTypePayment().intValue() <=0){
         err.put(0, messageSource.getMessage(ERROR_PAYMENTS.ERROR_TYPE_PAYMEN.getValue(), null, Locale.getDefault()));                
         return null;
        }
        Long numTranfer = paymentsRepository.count() +1L+START_SECUENCE_PAYMENT_NUMBER;
        payment.setNumberOfTransfer(numTranfer.toString());
        payment.setPayday(dateformat.parse(dto.getDatePay()));
        Float totalTax=getTax(dto.getDetails());
        payment.setTotalTax(totalTax);
        Float subTotal=getTotal(dto.getDetails());
        Float total =totalTax + subTotal ;
        payment.setTotalPayment(total);
        payment.setSubtotal(subTotal);
        payment.setTypePayment(dto.getTypePayment().intValue());
        payment.setUserRegister(userRegister);
        return payment;
    }
      /**
       * Sum all totals
       * @param listDet
       * @return
       */
     private Float getTotal(List<DetailPaymentDTO> listDet){
         BigDecimal total = new BigDecimal ("0");
         for(DetailPaymentDTO det:listDet){
            total = total.add(det.getTotal());
         }
         return total.floatValue();
     }  
     /**
      * Sum all taxes
      * @param listDet
      * @return
      */
     private Float getTax(List<DetailPaymentDTO> listDet){
         BigDecimal tax = new BigDecimal ("0");
         for(DetailPaymentDTO det:listDet){
            tax = tax.add(det.getTax());
         }
         return tax.floatValue();
     }  
     /**
      * Method that validates and builds the payment detail list
      * @param listDet
      * @param idPayment
      * @return
      * @throws GenericBZKException 
      */
      private List <DetailPayment> getListDetail(List<DetailPaymentDTO> listDet,int idPayment,Map <Integer,String>  err) throws GenericBZKException{
         List <DetailPayment> list = new ArrayList <DetailPayment>();
         Integer cont =1;
         String message ="";
         for(DetailPaymentDTO det:listDet){            
            if(det.getTax().floatValue()>det.getTotal().floatValue()) {
               message = messageSource.getMessage(ERROR_PAYMENTS.ERROR_TAX_TOTAL.getValue(), null, Locale.getDefault()).replace("%d",det.getIdStudent().toString());
               err.put(1,message);
               logger.debug("1.0 getListDetail null..");
               return null;
            }                                
            if(det.getTax().floatValue()<0){
               message =  messageSource.getMessage(ERROR_PAYMENTS.ERROR_TAX.getValue(), null, Locale.getDefault()).replace("%d",det.getIdStudent().toString());
                err.put(1,message);                                
                logger.debug("2.0 getListDetail null..");
                return null;
            }
            if(det.getTotal().floatValue()<=0){
                 message = messageSource.getMessage(ERROR_PAYMENTS.ERROR_TOTAL.getValue(), null, Locale.getDefault()).replace("%d",det.getIdStudent().toString());
                err.put(1,message);                
                logger.debug("3.0 getListDetail null..");
                return null;
            }
            if(det.getIdProduct()<=0){                
                message = messageSource.getMessage(ERROR_PAYMENTS.ERROR_PRODUCT.getValue(), null, Locale.getDefault()).replace("%d",det.getIdStudent().toString());
                err.put(1,message);                
                logger.debug("4.0 getListDetail null..");
                return null;
            }
            if(det.getIdStudent()<=0){
                message = messageSource.getMessage(ERROR_PAYMENTS.ERROR_STUDENT.getValue(), null, Locale.getDefault()).replace("%d", det.getIdStudent().toString());
                err.put(1, message);
                logger.debug("5.0 getListDetail null..");
                return null;
            }
            DetailPayment entity = new DetailPayment();
            entity.setComment(det.getComment());
            entity.setIdPayment(idPayment);
            entity.setIdProduct(det.getIdProduct().intValue());
            entity.setPayment(det.getTotal());
            entity.setTax(det.getTax());
            entity.setIdStudent(det.getIdStudent().intValue());
            list.add(entity);
            cont++;
         }
         return list;
     } 
    /**
     * Valid Field
     *
     * @param form
     * @return
     * @throws GenericBZKException
     */
    public List<String> validField(PaymentForm form) throws GenericBZKException, ParseException {
        List<String> listErr = new ArrayList<String>();
        prod = productsService.getProduct(Long.parseLong(form.getIdProduct()));
        if (prod == null) {
            throw new GenericBZKException("No exit product valid");
        }
        if ((form.getTotalTax() > form.getTotalPayment())) {
            listErr.add(messageSource.getMessage(ERROR_PAYMENTS.ERROR_TAX.getValue(), null, Locale.getDefault()));
        }
        /**
         * Set Tax
         */
        if (prod.getTax() > 0F && (form.getTotalTax() == 0F || form.getTotalTax() == null)) {
            BigDecimal tax = new BigDecimal(form.getTotalPayment() * prod.getTax());
            tax = tax.setScale(2, RoundingMode.HALF_UP);
            form.setTotalTax(tax.floatValue());
        }

        if (form.getDateRegister().compareTo(dateformat.parse(form.getDatePay())) < 0) {
            listErr.add(messageSource.getMessage(ERROR_PAYMENTS.ERROR_DAY_PAYMENT.getValue(), null, Locale.getDefault()));
        }

        return listErr;
    }

    /**
     * Method return product payment description
     *
     * @return
     * @throws com.dojogrouppty.error.GenericBZKException
     */
    public String productPaymentDescription() throws GenericBZKException {

        if (prod == null) {
            throw new GenericBZKException("No exit product valid");
        }
        SystemCodes cod = systemCodesRepository.findOne((long) prod.getType());

        return cod.getDescription();
    }
    /**
     * 
     * @param id
     * @return
     * @throws GenericBZKException 
     */
    public String amountPayableJson(Long id) throws GenericBZKException{
       Products p = productsService.getProduct(id);
       AmountPayableDTO dto=null;
        if (p == null) {
            throw new GenericBZKException("No exit product valid");
        }
        else{
            BigDecimal unitPrice =new BigDecimal(p.getUnitPrice());
            BigDecimal base = new BigDecimal(100);
            BigDecimal tax = new BigDecimal(p.getTax()).divide(base).multiply(unitPrice);
            tax = tax.setScale(2, RoundingMode.HALF_UP);
            unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
            BigDecimal total = new BigDecimal(p.getUnitPrice()* p.getTax());
            total=total.add(unitPrice);
            total = total.setScale(2, RoundingMode.HALF_UP);
            logger.debug("unitPrice:"+unitPrice.floatValue()+", total:"+total.floatValue()+", tax:"+tax.floatValue());
            dto = new AmountPayableDTO(unitPrice.floatValue(),total.floatValue(),tax.floatValue(),id);
        }
        return  gson.toJson(dto);
    }
    /**
     * Save the payment with your detail
     * @param dto
     * @param userRegister
     * @return
     * @throws GenericBZKException 
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class,ParseException.class,GenericBZKException.class})
    public synchronized PaymentResponse savePayment(PaymentDTO dto, String userRegister) throws GenericBZKException {
        PaymentResponse resp = new PaymentResponse();
        resp.setOk(NO_OK);
        Map <Integer,String> map = new HashMap<Integer, String>();
        try {
            String err = messageSource.getMessage(ERROR_PAYMENTS.ERROR_SAVE_PAYMENT.getValue(), null, Locale.getDefault());
            Payments payment = build(dto, userRegister, map);
            if (payment == null) {
                err = map.get(0);
                resp.setMessage(err);
                logger.debug("1.0 savePayment null..");
                return resp;
            } else {
                payment = paymentsRepository.save(payment);
                if (payment == null || payment.getIdPayment() <= 0) {                        
                    resp.setMessage(messageSource.getMessage(ERROR_PAYMENTS.ERROR_SAVE_PAYMENT.getValue(), null, Locale.getDefault()));
                    logger.debug("2.0 savePayment null..");              
                    return resp;
                }
                List<DetailPayment> list = getListDetail(dto.getDetails(), payment.getIdPayment(), map);
                if (list == null || list.isEmpty()) {
                    logger.debug("3.0 savePayment null");                   
                    resp.setMessage(map.get(1));                    
                } else {
                    detailPaymentRepository.save(list);
                    resp.setNumPayment(Long.parseLong(payment.getNumberOfTransfer()));
                    resp.setTotalPayment(payment.getTotalPayment());
                    resp.setTotalTax(payment.getTotalTax());
                    resp.setSubTotal(payment.getSubtotal());
                    resp.setOk(OK);
                    String message = messageSource.getMessage(MESSAGE_PAYMENT_OK, null, Locale.getDefault()).replace("%d", String.valueOf(payment.getNumberOfTransfer()));
                    resp.setMessage(message);
                }
            }
        } catch (ParseException ex) {
            logger.error("Error ParserException", ex);
            return resp;
        }
        return resp;
    }
    
}
