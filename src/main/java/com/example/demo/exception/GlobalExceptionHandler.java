package com.example.demo.exception;

import org.springframework.ui.ModelMap;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW="error";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest red,Exception e)throws Exception{

        ModelAndView mav=new ModelAndView();
        mav.addObject("exception",e);
        mav.addObject("url",red.getRequestURL());

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
