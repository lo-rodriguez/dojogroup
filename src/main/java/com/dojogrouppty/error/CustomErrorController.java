package com.dojogrouppty.error;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
class CustomErrorController {
private static final Logger logger =
		LoggerFactory.getLogger(CustomErrorController.class);
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code> element.
	 */
	@GetMapping("generalError")
	public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
            logger.debug("Entrando al metodo generalError");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		String message = MessageFormat.format("{0} returned for {1} with message {2}",
			statusCode, requestUri, exceptionMessage
		);         
		model.addAttribute("errorMessage", message);
                logger.debug("Error:"+message);
                logger.debug("Saliendo al metodo generalError");
		return "error/general";
	}
        

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
                    logger.error(throwable.getMessage());
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}
}
