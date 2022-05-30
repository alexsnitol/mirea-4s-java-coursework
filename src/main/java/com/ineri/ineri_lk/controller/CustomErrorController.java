package com.ineri.ineri_lk.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("error_other");

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Object exceptionType = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object requestURI = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        Object servletName = request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);

        if (exception != null) {
            mv.addObject("exception", exception.toString());
        }
        if (exceptionType != null) {
            mv.addObject("exceptionType", exceptionType.toString());
        }
        if (message != null) {
            mv.addObject("message", message.toString());
        }
        if (requestURI != null) {
            mv.addObject("requestURI", requestURI.toString());
        }
        if (servletName != null) {
            mv.addObject("servletName", servletName.toString());
        }

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            mv.addObject("statusCode", statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                mv = new ModelAndView("error_404");
            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
        }

        return mv;
    }

}
