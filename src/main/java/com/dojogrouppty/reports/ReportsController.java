/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.reports;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.DateUtils;
import com.dojogrouppty.common.MODULES;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.REPORTS_OPTION;
import com.dojogrouppty.error.GenericBZKException;
import com.dojogrouppty.products.ProductsService;
import com.dojogrouppty.students.StudentService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lrodriguezn
 */
@Controller
public class ReportsController extends ParentControllerService {

    @Autowired
    private ProductsService productsService;
    @Autowired
    private CatalogsService catalogsService;
    @Autowired
    private ReportsService reportsService;
    @Autowired
    private StudentService studentService;
    private static final org.slf4j.Logger logger
            = LoggerFactory.getLogger(ReportsController.class);

    /**
     * method to deploy all views of reports
     *
     * @param model
     * @param option
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String reportView(Model model, @RequestParam(REPORT_OPTION) Short option, HttpSession httpSession) {
        httpSession.setAttribute(REPORT_OPTION, option);
        model.addAttribute(VIEW_OPTION, option);
        model.addAttribute(FORM_ACCION, ACCION_GENERATE_REPORT);
        model.addAttribute(REPORT_FORM_OBJ, new ReportForm());
        model.addAttribute(REPORT_HEAD, new ReportHeaderDTO());
        model.addAttribute(SEARCH_STUDENT_ACCION, ACCION_REPORT_HISTORY_BY_STUDENT_ID);
        switch (REPORTS_OPTION.convertOption(option)) {
            case ADMINISTRATIVE_REPORT:
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(DateUtils.addDay(DEFAULT_DAYS_FOR_SEARCH)));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(new Date()));
                model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
                model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
                break;
            case PAYMENT_HISTORY_BY_STUDENT:
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(DateUtils.addDay(DEFAULT_DAYS_FOR_SEARCH)));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(new Date()));
                model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
                model.addAttribute(ACTIVATE_PAYMENT_HISTORY_STUDENT, Boolean.TRUE);
                model.addAttribute(ACTIVATE_TYPE_PRODUCT, Boolean.TRUE);
                model.addAttribute(PRODUCTS, productsService.getActiveProducts());
                model.addAttribute(DESCPTION_STUDENTS, studentService.getDescriptionStudentDTO());
                break;
            case RECEIPTS_REPORT:
                model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.FALSE);
                model.addAttribute(ACTIVATE_TYPE_PAYMEN_RECEIPTS, Boolean.TRUE);
                model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
                break;
            case REPORT_FOR_DOWNLOAD:
                model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
                model.addAttribute(ACTIVATE_TYPE_PRODUCT, Boolean.TRUE);
                model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(DateUtils.addDay(DEFAULT_DAYS_FOR_SEARCH)));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(new Date()));
                model.addAttribute(PRODUCTS, productsService.getActiveProducts());
                break;
            case STATE_STUDENT_ACCOUNT:
            	model.addAttribute(MAP_STATUS,catalogsService.getStatus());
            	 model.addAttribute(ACTIVATE_STATE_STUDENT, Boolean.TRUE);
            	break;
            
		default:
			break;
        }
        return REPORTS_VIEW;
    }

    /**
     * Method that generates the reports
     *
     * @param model
     * @param reportForm
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String generateReport(Model model, @ModelAttribute ReportForm reportForm, HttpSession httpSession) {
        Short option = (Short) httpSession.getAttribute(REPORT_OPTION);
        
        model.addAttribute(FORM_ACCION, ACCION_GENERATE_REPORT);
        model.addAttribute(REPORT_FORM_OBJ, reportForm);
        model.addAttribute(VIEW_OPTION, option);
        model.addAttribute(REPORT_HEAD, new ReportHeaderDTO());
        return getOption(model, reportForm, option);
    }
    /**
     * Method that obtains the history of the last 3 years of the student
     * 
     * @param model
     * @param reportForm
     * @param httpSession
     * @return String
     */                        
    @RequestMapping(value = "/getHistoryByStudentID", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String getHistoryByStudentID(Model model,  @RequestParam("idStudent") Integer idStudent, HttpSession httpSession) {
    	Short option = REPORTS_OPTION.PAYMENT_HISTORY_BY_STUDENT.getOption();
    	ReportForm reportForm = reportsService.getReportForm(idStudent);
        httpSession.setAttribute(REPORT_OPTION, option);
        model.addAttribute(VIEW_OPTION, option);
        model.addAttribute(FORM_ACCION, ACCION_GENERATE_REPORT);
        model.addAttribute(REPORT_FORM_OBJ, new ReportForm());
        model.addAttribute(REPORT_HEAD, new ReportHeaderDTO());
        model.addAttribute(ID_STUDENT, Long.valueOf(idStudent));
    	return getOption(model, reportForm, option,Long.valueOf(idStudent));
    }
    private String getOption(Model model,ReportForm reportForm, Short option) {
    	return getOption(model,reportForm,option,0L);
    }
    private String getOption(Model model,ReportForm reportForm, Short option,Long idStudent) {
    	Map<?, ?> map = null;
        switch (REPORTS_OPTION.convertOption(option)) {
        case ADMINISTRATIVE_REPORT:
            model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
            try {
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getInitialDate())));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getFinalDate())));              
                model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
                map = reportsService.administrativeReport(reportForm);
                if (map.containsKey(GENERAL_MODAL_MESSAGE)) {
                    model.addAttribute(GENERAL_MODAL_MESSAGE, map.get(GENERAL_MODAL_MESSAGE));
                } else {
                    model.addAttribute(REPORT_DETAIL_LIST, map.get(REPORT_DETAIL_LIST));
                    model.addAttribute(REPORT_FOOD, map.get(REPORT_FOOD));
                    model.addAttribute(REPORT_HEAD, map.get(REPORT_HEAD));
                }
            } catch (ParseException ex) {
                logger.error("Error al parsear las fechas, ex: " + ex.getMessage());
                return ERROR_ACTION;
            }
            break;
        case PAYMENT_HISTORY_BY_STUDENT:
            try {
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getInitialDate())));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getFinalDate())));
                model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
                model.addAttribute(ACTIVATE_PAYMENT_HISTORY_STUDENT, Boolean.TRUE);
                model.addAttribute(ACTIVATE_TYPE_PRODUCT, Boolean.TRUE);
                model.addAttribute(PRODUCTS, productsService.getActiveProducts());
                model.addAttribute(SEARCH_STUDENT_ACCION, ACCION_READONLY_STUDENT);
                model.addAttribute(_MODULE, MODULES.REPORTS.moduleOption());
                model.addAttribute(DESCPTION_STUDENTS, studentService.getDescriptionStudentDTO(idStudent));
                map = reportsService.paymentHistoryByStudent(reportForm);
                if (map.containsKey(GENERAL_MODAL_MESSAGE)) {
                    model.addAttribute(GENERAL_MODAL_MESSAGE, map.get(GENERAL_MODAL_MESSAGE));
                } else {
                    model.addAttribute(REPORT_DETAIL_LIST, map.get(REPORT_DETAIL_LIST));
                    model.addAttribute(REPORT_FOOD, map.get(REPORT_FOOD));
                    model.addAttribute(REPORT_HEAD, map.get(REPORT_HEAD));
                    model.addAttribute(SHOW_IMAGE, studentService.existPhoto(new Long(reportForm.getIdStudent())));
                    model.addAttribute(PROFILE_STUDENT, map.get(PROFILE_STUDENT));
                }
            } catch (ParseException ex) {
                logger.error("Error al parsear las fechas, ex: " + ex.getMessage());
                return ERROR_ACTION;
            }
            break;
        case RECEIPTS_REPORT:
            model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.FALSE);
            model.addAttribute(ACTIVATE_TYPE_PAYMEN_RECEIPTS, Boolean.TRUE);
            model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
            map = reportsService.receiptsReport(reportForm);
            if (map.containsKey(GENERAL_MODAL_MESSAGE)) {
                model.addAttribute(GENERAL_MODAL_MESSAGE, map.get(GENERAL_MODAL_MESSAGE));
            } else {
                model.addAttribute(REPORT_DETAIL_LIST, map.get(REPORT_DETAIL_LIST));
                model.addAttribute(REPORT_FOOD, map.get(REPORT_FOOD));
                model.addAttribute(REPORT_HEAD, map.get(REPORT_HEAD));
            }
            
            break;
        case REPORT_FOR_DOWNLOAD:
            model.addAttribute(ACTIVATE_DATES_RANGES, Boolean.TRUE);
            model.addAttribute(ACTIVATE_TYPE_PRODUCT, Boolean.TRUE);
            model.addAttribute(PRODUCTS, productsService.getActiveProducts());
            model.addAttribute(TYPES_PAYMENTS, catalogsService.getSystemCodesByGroup(PAYMENTS));
            try {
                model.addAttribute(INITIAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getInitialDate())));
                model.addAttribute(FINAL_DATE, DateFormat.getInstance().format(dateformat.parse(reportForm.getFinalDate())));
                map = reportsService.reportForDownload(reportForm);
                if (map.containsKey(GENERAL_MODAL_MESSAGE)) {
                    model.addAttribute(GENERAL_MODAL_MESSAGE, map.get(GENERAL_MODAL_MESSAGE));
                } else {
                    model.addAttribute(REPORT_DETAIL_LIST, map.get(REPORT_DETAIL_LIST));
                    model.addAttribute(REPORT_FOOD, map.get(REPORT_FOOD));
                    model.addAttribute(REPORT_HEAD, map.get(REPORT_HEAD));
                }
            } catch (ParseException ex) {
                logger.error("Error al parsear las fechas, ex: " + ex.getMessage());
                return ERROR_ACTION;
            }
            break;
        case STATE_STUDENT_ACCOUNT:
            try {
            	model.addAttribute(MAP_STATUS,catalogsService.getStatus());
           	 	model.addAttribute(ACTIVATE_STATE_STUDENT, Boolean.TRUE);
                map = reportsService.stateStudentAccount(reportForm);
                model.addAttribute(SEARCH_STUDENT_ACCION, ACCION_REPORT_HISTORY_BY_STUDENT_ID);
                if (map.containsKey(GENERAL_MODAL_MESSAGE)) {
                    model.addAttribute(GENERAL_MODAL_MESSAGE, map.get(GENERAL_MODAL_MESSAGE));
                } else {
                    model.addAttribute(REPORT_DETAIL_LIST, map.get(REPORT_DETAIL_LIST));
                    model.addAttribute(REPORT_FOOD, map.get(REPORT_FOOD));
                    model.addAttribute(REPORT_HEAD, map.get(REPORT_HEAD));
                }
                model.addAttribute(STATES_STUDENTS_ACCOUNT, map.get(STATES_STUDENTS_ACCOUNT));
            } catch (GenericBZKException e) {
                logger.error("Error generar el informe, e: " + e.getMessage());
                return ERROR_ACTION;
            }
            break;
	default:
		break;
    }
        return REPORTS_VIEW;
    }
}
