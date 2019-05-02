/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.account;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.VARS_SESSION;
import com.dojogrouppty.error.GenericBZKException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author lrodriguezn
 */
@Controller
public class AccountMaintenanceController   extends ParentControllerService {
    @Autowired
    private AppProfileRepository appProfileRepository;
    @Autowired
    private  AccountService accountService;
    @Autowired
    private CatalogsService catalogsService;
    private static final Logger logger
    = LoggerFactory.getLogger(AccountMaintenanceController.class);
    @RequestMapping(value = "/accountMaintenance", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    public String account(Model model, @Valid @ModelAttribute AccountForm accountMaintenanceForm){
        model.addAttribute(FORM_ACCION, FORM_ACCION_ACCOUNT_MAINTENANCE);
        model.addAttribute(accountMaintenanceForm);
        return ACCOUNT_VIEW;
    }
    /**
     * Method account
     * @param model
     * @return 
     */
    @RequestMapping(value = "/accountMaintenance", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    public String account(Model model){
        AccountForm form = new AccountForm();
        form.setListAccountDto(accountService.getUserEnabled());
        setModelAccount(model,form);
        return ACCOUNT_VIEW;
    }  
    /**
     * Method set model account
     * @param model
     * @param form 
     */
    private void setModelAccount(Model model, AccountForm form){
        model.addAttribute(FORM_ACCION, FORM_ACCION_ACCOUNT_MAINTENANCE);
        model.addAttribute(ACCOUNT_FORM_OBJ, form);
        model.addAttribute(LIST_ACCION_FORM_PRODUCTS, catalogsService.getSystemCodesByGroup(SYSTEM_SYSACCION));
        model.addAttribute(LIST_ROLES,appProfileRepository.findAll());
    }
    /**
     * Method add account
     * @param model
     * @param accountForm
     * @return 
     */
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String  addAccount(Model model,@Valid @ModelAttribute AccountForm accountForm){
        accountService.addUser(model, accountForm);
        accountForm.setListAccountDto(accountService.getUserEnabled());
        setModelAccount(model,accountForm);
       return ACCOUNT_VIEW; 
    }
    /**
     * Method for update account
     * @param model
     * @param accountForm
     * @return 
     */
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String  updateAccount(Model model,@Valid @ModelAttribute AccountForm accountForm ){
        accountService.updateUsers(model, accountForm);
        accountForm.setListAccountDto(accountService.getUserEnabled());
        setModelAccount(model,accountForm);
       return ACCOUNT_VIEW; 
    }

	@Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN" })
	@RequestMapping(value = "/editPersonalInfo", method = RequestMethod.GET)
	public String editPersonalInfo(Model model) {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        String userRegister = (String) session.getAttribute(VARS_SESSION.REGISTER_USER.varOption());
        if (userRegister != null) {
        	PersonalInfoForm form =null;
			try {
				form = accountService.getCurrentUser(userRegister);
			} catch (GenericBZKException e) {
				 logger.error("Error retrieving the current user");
			      return ERROR_ACTION;
			}
        	model.addAttribute(EDIT_PERSOANLA_INFO_FORM_OBJ, form);
        	model.addAttribute(FORM_ACCION, FORM_EDIT_PERSOANLA_INFO);
		}else {
		  logger.error("Error retrieving the current user");
	      return ERROR_ACTION;
		}
		return EDIT_PERSONAL_INFO;

	}
}
