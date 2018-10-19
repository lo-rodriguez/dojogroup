package com.dojogrouppty.home;

import com.dojogrouppty.common.ParentControllerService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AboutController extends ParentControllerService{
    @Value("${app.version}")
    private String version;
     @Value("${app.developers}")
    private String developers;
    @Value("${app.licence}")
    private String licence;
	@ModelAttribute("module")
	String module() {
		return "about";
	}

	@GetMapping("/about")
	String about(Model model,HttpSession httpSession) {
            model.addAttribute(ENABLE_ABOUT, Boolean.TRUE);
            model.addAttribute(APP_VERSION, version);
            model.addAttribute(APP_DEVELOPER,developers);
            model.addAttribute(APP_LICENCE,licence);
        return DASHBOARD_VIEW_NAME;
	}
}
