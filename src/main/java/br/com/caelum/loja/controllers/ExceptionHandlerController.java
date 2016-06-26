package br.com.caelum.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(Exception.class)
    public ModelAndView trataExceptionGenerica(Exception e) {
        e.printStackTrace();
        
        ModelAndView view = new ModelAndView("error");
        view.addObject("exception", e);
        return view;
    }
}