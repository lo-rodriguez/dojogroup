/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.VARS_SESSION;
import com.dojogrouppty.error.GenericBZKException;
import com.dojogrouppty.payments.PaymentsController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lrodriguezn
 */
@Controller
public class ProductsController extends ParentControllerService{
    @Autowired
    private ProductsService productsService;
    @Autowired
    private CatalogsService catalogsService;
    private static final Logger logger
            = LoggerFactory.getLogger(PaymentsController.class);
    @Autowired
    MessageSource messageSource;
    /**
     * Method for process product maintences
     * @param model
     * @return View products maintenance
     */
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/productsMaintenance", method = RequestMethod.GET)
    String productMaintences(Model model){
    ProductsForm form = new ProductsForm();
    form.setListDto(productsService.getProducts());      
    model.addAttribute(form); 
    model.addAttribute(LIST_ACCION_FORM_PRODUCTS, catalogsService.getSystemCodesByGroup(SYSTEM_SYSACCION));
    model.addAttribute(FORM_ACCION, ACCION_PRODUCTS);
    model.addAttribute(FORM_ACCION_OTHER, ACCION_PRODUCTS);
    model.addAttribute(TYPE_PRODUCTS, catalogsService.getTypesProduct());
    return PRODUCTS_MAINTENANCE;
    }
    /**
     * Method for process product maintences
     * @param model
     * @param productsForm
     * @return View products maintenance
     */
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/updateProducts", method = RequestMethod.POST)
    String updateProduct(Model model,@ModelAttribute ProductsForm productsForm,HttpSession session){      
    model.addAttribute(LIST_ACCION_FORM_PRODUCTS, catalogsService.getSystemCodesByGroup(SYSTEM_SYSACCION));
    model.addAttribute(TYPE_PRODUCTS, catalogsService.getTypesProduct());
        String userRegister = (String) session.getAttribute(VARS_SESSION.REGISTER_USER.varOption());
        if (userRegister == null) {
            logger.error("Error al obtener los datos de sesion ..");
        } else {
            List<String> listErr = new ArrayList<String>();
            Map <Integer,String> err = productsService.getUpdateProducts(productsForm, userRegister);                    
            if(err.containsKey(GENERAL_MODAL_MESSAGE_KEY)){
                model.addAttribute(GENERAL_MODAL_MESSAGE, err.get(GENERAL_MODAL_MESSAGE_KEY));
            }
            else{
                for (Map.Entry<Integer, String> entry : err.entrySet()) {
                    listErr.add((String) entry.getValue());
                }
                model.addAttribute(LIST_ERR, listErr);
            }
        }
        model.addAttribute(productsForm); 
        return PRODUCTS_MAINTENANCE;
    }
       /**
     * Method for process product maintences
     * @param model
     * @param productsForm
     * @return View products maintenance
     * @throws CloneNotSupportedException 
     * @throws GenericBZKException 
     */
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/addProducts", method = RequestMethod.POST)
    String addProducts(Model model,@ModelAttribute ProductsForm productsForm,HttpSession session) throws ParseException, CloneNotSupportedException, GenericBZKException{     
    model.addAttribute(LIST_ACCION_FORM_PRODUCTS, catalogsService.getSystemCodesByGroup(SYSTEM_SYSACCION));
    String userRegister = (String) session.getAttribute(VARS_SESSION.REGISTER_USER.varOption());
    List<String> listErr = new ArrayList<String>();
        if (userRegister == null) {
            logger.error("Error al obtener los datos de sesion ..");
            String message = messageSource.getMessage(MESSAGE_ERROR_SESSION, null, Locale.getDefault());
            listErr.add(message);
            model.addAttribute(LIST_ERR, listErr);
        } else {
            Map <String,Object> rep = productsService.getAddProducts(productsForm, userRegister);
            if(rep.containsKey(GENERAL_MODAL_MESSAGE)){
              model.addAttribute(GENERAL_MODAL_MESSAGE, rep.get(GENERAL_MODAL_MESSAGE));  
            } 
            else if(rep.containsKey(ERROR_FORM_PRODUCT)){
             listErr.add((String)rep.get(ERROR_FORM_PRODUCT));
             model.addAttribute(LIST_ERR, listErr);
            }
        }
    model.addAttribute(productsForm);   
    model.addAttribute(TYPE_PRODUCTS, catalogsService.getTypesProduct());
    return PRODUCTS_MAINTENANCE;
    }
}
