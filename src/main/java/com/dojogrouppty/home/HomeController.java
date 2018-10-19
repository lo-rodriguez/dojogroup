package com.dojogrouppty.home;

import com.dojogrouppty.activities.ActivitiesService;
import com.dojogrouppty.common.MODULES;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.VARS_SESSION;
import com.dojogrouppty.payments.PaymentsService;
import com.dojogrouppty.products.ProductsService;
import com.dojogrouppty.reports.ReportsService;
import com.dojogrouppty.students.StudentService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.SpringVersion;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController  extends ParentControllerService{
   @Autowired
   private StudentService studentService;
   @Autowired
   ProductsService productsService;
   @Autowired
   PaymentsService paymentsService;
   @Autowired
   ActivitiesService activitiesService;
   @Autowired
    private ReportsService reportsService;
    private static final Logger logger
            = LoggerFactory.getLogger(HomeController.class);
	@ModelAttribute("module")
	String module() {
		return "home";
	}

	@GetMapping("/")
	String index(Principal principal, Model model) {
		model.addAttribute("springVersion", SpringVersion.getVersion());                
		//return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
                return LOGIN_VIEW_NAME;
	}
       @GetMapping("/dashboard")
       String  dashboard( Model model,HttpSession httpSession){
           logger.debug("Into method dashboard..");
           if (httpSession != null && httpSession.getAttribute(VARS_SESSION.REGISTER_USER.varOption()) == null) {
               Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
               String currentPrincipalName = authentication.getName();
               if (currentPrincipalName != null) {
                   logger.debug("CurrentPrincipalName: " + currentPrincipalName);
                   httpSession.setAttribute(VARS_SESSION.REGISTER_USER.varOption(), currentPrincipalName);
               }
           }
           model.addAttribute(_MODULE, MODULES.HOME.moduleOption());
           model.addAttribute(NEW_STUDENT_REGISTRATIONS, studentService.accountLatestInscriptions());
           model.addAttribute(CURRENT_PRODUCT_ACCOUNT, productsService.currentProductAccount());
           model.addAttribute(PAYMENT_NOTIFICATION, paymentsService.amountOfPaymentsMade());
           model.addAttribute(ACTIVITIES_NOTIFICATION, activitiesService.accountPendingActivitiesToExpire());
           return DASHBOARD_VIEW_NAME;
        }
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/typesPaymentVsYears",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String typesPaymentVsYears() {
        return reportsService.typesPaymentVsYears();
    }
}
