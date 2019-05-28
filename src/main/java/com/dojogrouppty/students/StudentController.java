/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.catalogs.SystemCodes;
import com.dojogrouppty.common.DISABLED;
import com.dojogrouppty.common.MODULES;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.STATUS;
import com.dojogrouppty.common.STUDENT_SEARCH_OPTIONS;
import com.dojogrouppty.error.GenericBZKException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author lrodriguezn
 */
@Controller
public class StudentController extends ParentControllerService {

    @Autowired
    private CatalogsService catalogsService;
    @Autowired
    private StudentService studentService;
    private static final Logger logger
            = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    MessageSource messageSource;
    

    /**
     * Proceso de alta de estudiante
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String addstudent(Model model) {
        logger.debug("Entrando al metodo addstudent..");
        //StudentForm
        model.addAttribute(STUDENT_FORM_OBJ, new StudentForm());
        model.addAttribute(DISABLED__, DISABLED.ENABLED.getValue());
        model.addAttribute(EDIT_MODE_STUDENT, Boolean.FALSE);
        model.addAttribute(FORM_ACCION, ACCION_ADD_STUDENT);
        model.addAttribute(_MODULE, MODULES.STUDENT.moduleOption());
        model.addAttribute(SHOW_IMAGE, Boolean.FALSE);
        model.addAttribute(BIRTHDATE, DateFormat.getInstance().format(new Date()));
        addCodesModelo(model);
        return FORM_STUDENT_VIEW_NAME;
    }

    /**
     * method by add student
     *
     * @param model
     * @param studentForm
     * @param errors
     * @param ra
     * @return
     */
    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String addstudent(Model model, @Valid @ModelAttribute StudentForm studentForm, Errors errors, RedirectAttributes ra) {
        String addStudentMessage = "";
        try {
            logger.debug("Entrando al metodo addstudent post");
            addCodesModelo(model);
            model.addAttribute(FORM_ACCION, ACCION_ADD_STUDENT);
            model.addAttribute(_MODULE, MODULES.STUDENT.moduleOption());
            model.addAttribute(SHOW_IMAGE, Boolean.FALSE);
            if (errors.hasErrors()) {
                for (ObjectError err : errors.getAllErrors()) {
                    logger.debug("Error:" + err.getCode());
                }
                return FORM_STUDENT_VIEW_NAME;
            } else {
                if (studentService.validEmailStudent(studentForm.getEmail(), 0)) {
                    Student st = studentService.saveStudent(studentForm);
                    studentForm.setId(st.getIdStudent());
                    if (studentService.savePhoto(studentForm)) {
                        logger.debug("Save image..");
                        model.addAttribute(SHOW_IMAGE, Boolean.TRUE);
                    } else {
                        logger.error("Dont save image");
                    }
                    addStudentMessage = messageSource.getMessage(FINAL_MESSAGE_ADD_STUDENT, null, Locale.getDefault());
                    addStudentMessage = addStudentMessage.replace("{firstName}", st.getFirstName()).replace("{id}", st.getIdStudent().toString());
                    logger.debug("addStudentMessage:" + addStudentMessage);
                    model.addAttribute(GENERAL_MODAL_MESSAGE, addStudentMessage);
                    model.addAttribute(EDIT_MODE_STUDENT, Boolean.FALSE);
                    model.addAttribute(OK_ADD_STUDENT, OK);
                    model.addAttribute(DISABLED__, DISABLED.DISABLED_OFF.getValue());
                } else {
                    model.addAttribute(EDIT_MODE_STUDENT, Boolean.FALSE);
                    model.addAttribute(OK_ADD_STUDENT, NO_OK);
                    List<String> listErr = new ArrayList<String>();
                    String message = messageSource.getMessage(ERROR_EMAIL_STUDENT, null, Locale.getDefault());
                    listErr.add(message);
                    model.addAttribute(LIST_ERR, listErr);
                }
                logger.info("studentForm.getBirthdate(): " + studentForm.getBirthdate());
                model.addAttribute(BIRTHDATE, DateFormat.getInstance().format(dateformat.parse(studentForm.getBirthdate())));

            }
        } catch (ParseException | IOException | GenericBZKException | NoSuchMessageException ex) {
            logger.error("Error insert " + ex.getMessage());
            return ERROR_ACTION;
        }        
        return FORM_STUDENT_VIEW_NAME;
    }

    /**
     * Method to obtain the student's photo
     *
     * @param model
     * @param idStudent
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/studentImage/{idStudent}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public byte[] downloadImage(Model model,
            @PathVariable("idStudent") Integer idStudent, HttpServletResponse response) throws IOException {
//      response.setContentType("image/jpeg");
        File serverFile = new File(LOCATION + idStudent + "." + TYPE_IMAGE_SUPPORTED_PHOTO);
        if(!serverFile.exists()){
          Student s = studentService.getStudent(new Long(idStudent));
          return s.getPhoto();
        }
        return Files.readAllBytes(serverFile.toPath());
    }

    /**
     * method by edit student
     *
     * @param model
     * @param id
     * @return student/formStudent
     */
    @RequestMapping(value = "/editstudent", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String edittudent(Model model, @RequestParam("idStudent") long id) throws IOException {
        try {
            logger.debug("Into  edittudent with id=%d", id);
            setModel(model,id);
            addCodesModelo(model);         
            return FORM_STUDENT_VIEW_NAME;
        } catch (GenericBZKException ex) {
            logger.error("Error insert " + ex.getMessage());
            return ERROR_ACTION;
        }
    }
    
    
    /**
     * method student only reading
     *
     * @param model
     * @param id
     * @return student/formStudent
     */
    @RequestMapping(value = "/readOnlyStudent", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String readOnlyStudent(Model model, @RequestParam("idStudent") long id) throws IOException {
        try {
            logger.debug("Into  readOnlyStudent with id=%d", id);           
            setModel(model,id);
            model.addAttribute(DISABLED__, DISABLED.DISABLED_OFF.getValue());
            model.addAttribute(_MODULE, MODULES.REPORTS.moduleOption());
            addCodesModelo(model);        
            return FORM_STUDENT_VIEW_NAME;
        } catch (GenericBZKException ex) {
            logger.error("Error insert " + ex.getMessage());
            return ERROR_ACTION;
        }
    }
    
    private void setModel(Model model,long id) throws GenericBZKException, IOException {
    	 StudentForm form = studentService.loadFormStudent(id);
         model.addAttribute(REGISTRATION_DATE_ATRIBUTE,messageSource.getMessage(REGISTRATION_DATE, null, Locale.getDefault()).replace("%s", form.getDayOfIncome()));
         model.addAttribute(FORM_ACCION, ACCION_EDIT_STUDENT);
         model.addAttribute(SHOW_IMAGE, Boolean.FALSE);
         model.addAttribute(_MODULE, MODULES.STUDENT.moduleOption());
         model.addAttribute(STUDENT_FORM_OBJ, form);
         model.addAttribute(DISABLED__, DISABLED.ENABLED.getValue());
         model.addAttribute(EDIT_MODE_STUDENT, Boolean.TRUE);
         model.addAttribute(BIRTHDATE, DateFormat.getInstance().format(new Date()));
         if (studentService.savePhoto(form.getData(), id)) {
             model.addAttribute(SHOW_IMAGE, Boolean.TRUE);
         }
    }

    @RequestMapping(value = "/editstudent", method = RequestMethod.POST)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String edittudent(Model model, @Valid @ModelAttribute StudentForm studentForm, Errors errors, RedirectAttributes ra) throws ParseException, IOException, GenericBZKException {
        String editStudentMessage = "";
         logger.debug("<<<<<<Into method edittudent >>>>>>");
        try{
        model.addAttribute(SHOW_IMAGE, Boolean.FALSE);
        model.addAttribute(FORM_ACCION, ACCION_EDIT_STUDENT);
        model.addAttribute(EDIT_MODE_STUDENT, Boolean.TRUE);
        model.addAttribute(_MODULE, MODULES.STUDENT.moduleOption());
        model.addAttribute(REGISTRATION_DATE_ATRIBUTE,messageSource.getMessage(REGISTRATION_DATE, null, Locale.getDefault()).replace("%s", studentForm.getDayOfIncome()));
        if (studentForm!=null && studentForm.getStatus() != null && STATUS.DELETED.getStatus().toString().equals(studentForm.getStatus())) {
            logger.debug("<<<<<<*************************************** >>>>>>");
              logger.debug("<<<2>>>");
            Student st = studentService.saveStudent(studentForm);
              logger.debug("<<<3>>>");
            editStudentMessage = messageSource.getMessage(FINAL_MESSAGE, null, Locale.getDefault());
            editStudentMessage = editStudentMessage.replace("{student}", st.getFirstName());
        } else if (errors.hasErrors()) {
             logger.debug("4.");
            for (ObjectError err : errors.getAllErrors()) {
                logger.debug("Error:" + err.getCode());
            }
            return FORM_STUDENT_VIEW_NAME;
        } else {
            logger.debug("<<<<<<*************************************** >>>>>>");
             logger.debug("5.");
            if (studentService.validEmailStudent(studentForm.getEmail(), studentForm.getId())) {
                Student st = studentService.saveStudent(studentForm);
                editStudentMessage = messageSource.getMessage(FINAL_MESSAGE_EDIT_STUDENT, null, Locale.getDefault());
                editStudentMessage = editStudentMessage.replace("{firstName}", st.getFirstName()).replace("{id}", st.getIdStudent().toString());
                logger.debug("editStudentMessage:%s",editStudentMessage);
                if (studentForm.getData() != null && studentService.savePhoto(studentForm.getData(), st.getIdStudent())) {
                    model.addAttribute(SHOW_IMAGE, Boolean.TRUE);
                }
            } else {
                logger.debug("6.");
                model.addAttribute(OK_ADD_STUDENT, NO_OK);
                List<String> listErr = new ArrayList<String>();
                String message = messageSource.getMessage(ERROR_EMAIL_STUDENT, null, Locale.getDefault());
                listErr.add(message);
                model.addAttribute(LIST_ERR, listErr);
            }
        }
        logger.debug("<<<<<<*************877************************ >>>>>>");
        model.addAttribute(OK_EDIT_STUDENT, OK);
        model.addAttribute(GENERAL_MODAL_MESSAGE, editStudentMessage);
        model.addAttribute(STUDENT_FORM_OBJ, studentForm);
        model.addAttribute(BIRTHDATE, DateFormat.getInstance().format(dateformat.parse(studentForm.getBirthdate())));
        model.addAttribute(DISABLED__, DISABLED.DISABLED_OFF.getValue());
        addCodesModelo(model);
         }catch(NullPointerException e){
            logger.error("Error of null pointer in:"+e.getMessage());
            throw new GenericBZKException("Null pointer exception:"+e.toString());
        }
        return FORM_STUDENT_VIEW_NAME;
    }

    @GetMapping("/studentSearch")
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})       
    String studentSearch(Model model, @RequestParam("option") short op) {
        logger.debug("Into method studentSearch..");
        model.addAttribute(STUDENTS_DTO, studentService.getStudentsEnable());
        STUDENT_SEARCH_OPTIONS option = STUDENT_SEARCH_OPTIONS.convertOption(op);
        model.addAttribute(SEARCH_STUDENT_OPTION, option.studentSearchOption());
        switch (option) {
            case SEARCH_EDIT_STUDENT:
                model.addAttribute(SEARCH_STUDENT_ACCION, ACCION_EDIT_STUDENT);
                model.addAttribute(_MODULE, MODULES.STUDENT.moduleOption());
                break;
            case SEARCH_PAYMENT_STUDENT:
                model.addAttribute(SEARCH_STUDENT_ACCION, ACCION_PAYMENT_STUDENT);
                model.addAttribute(_MODULE, MODULES.PAYMENT.moduleOption());
                break;
		default:
			break;
        }
        logger.debug("Saliendo del metodo studentSearch..");
        return PAGE_STUDENT_SEARCH;
    }

    /**
     * Add atributes in the model
     *
     * @param model
     */
    private void addCodesModelo(Model model) {
        logger.debug("<<1>>");
        List<SystemCodes> codes = catalogsService.getSystemCodesByGroup(SYSTEM_CODES_GENDERS);
        //Deshabilitar el DEBUG para el proceso de pase a producción
//        showCodes(SYSTEM_CODES_GENDERS, codes);
        model.addAttribute(GENDER_CATALOG, codes);
        codes = catalogsService.getSystemCodesByGroup(SYSTEM_CODES_CATEGORY);
//        showCodes(SYSTEM_CODES_CATEGORY, codes);
        logger.debug("<<2>>");
        model.addAttribute(CATEGORIES_CATALOG, codes);
        codes = catalogsService.getSystemCodesByGroup(SYSTEM_CODES_EMPLOYMENT);
//        showCodes(SYSTEM_CODES_EMPLOYMENT, codes);
        model.addAttribute(EMPLOYMENTS_CATALOG, codes);
        codes = catalogsService.getSystemCodesByGroup(SYSTEM_CODES_SIZES);
//        showCodes(SYSTEM_CODES_SIZES, codes);
        logger.debug("<<3>>");
        model.addAttribute(SIZES_CATALOG, codes);
        codes = catalogsService.getSystemCodesByGroup(SYSTEM_CODES_BLOODGRO);
//        showCodes(SYSTEM_CODES_BLOODGRO, codes);
        logger.debug("<<4>>");
        model.addAttribute(BLOODGROUPS_CATALOG, codes);
        model.addAttribute(KEY_MESSAGE_ALERT_SIZE_PHOTO, messageSource.getMessage(MESSAGE_ALERT_SIZE_PHOTO, null, Locale.getDefault()));
        model.addAttribute(KEY_MESSAGE_ALERT_ADD_STUDENT, messageSource.getMessage(MESSAGE_ALERT_EDIT_STUDENT, null, Locale.getDefault()));
        model.addAttribute(KEY_MAX_FILE_SIZE, getMaxFileSize());
        logger.debug("<<5>>");
    }

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return ERROR_ACTION;

    }

}
