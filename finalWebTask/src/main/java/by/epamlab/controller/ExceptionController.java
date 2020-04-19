package by.epamlab.controller;

import by.epamlab.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ProductNotFoundException.class)
    ModelAndView handleException(ProductNotFoundException e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }
}
