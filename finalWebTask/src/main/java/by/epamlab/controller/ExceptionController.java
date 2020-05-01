package by.epamlab.controller;

import by.epamlab.exception.HandlerNotFoundException;
import by.epamlab.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ModelAndView handleException(ProductNotFoundException e){
        LOGGER.info(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        LOGGER.info("NoHandlerFoundException: " + e.getMessage());
        ModelAndView modelAndView = new ModelAndView("defaultErrorPage");
        modelAndView.addObject("errMsg", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }
}
