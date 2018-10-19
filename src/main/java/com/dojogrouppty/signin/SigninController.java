package com.dojogrouppty.signin;

import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.VARS_SESSION;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController extends ParentControllerService {

    private static final Logger logger
            = LoggerFactory.getLogger(SigninController.class);
    @Autowired
    MessageSource messageSource;

     @RequestMapping(value = "/authenticationError", method = RequestMethod.GET)
    public String signin(Model model,HttpSession httpSession ,@RequestParam("error") short err) {
        logger.debug("Into authenticationError...");
        for(VARS_SESSION var:VARS_SESSION.values()){
            httpSession.removeAttribute(var.varOption());
        }       
         List<String> listErr = new ArrayList<String>();
         logger.debug("Going to load the authentication error..");
         listErr.add(messageSource.getMessage(MESSAGE_ERROR_AUTHENTICATION, null, Locale.getDefault()));
         model.addAttribute(LIST_ERR, listErr);       
        return LOGIN_VIEW_NAME;
    }
   @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(HttpSession httpSession ) {
        logger.debug("Entrando al metodo signin...");
        for(VARS_SESSION var:VARS_SESSION.values()){
            httpSession.removeAttribute(var.varOption());
        }        
        return LOGIN_VIEW_NAME;
    }

}
