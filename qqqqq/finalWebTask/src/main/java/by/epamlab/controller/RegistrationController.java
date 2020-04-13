package by.epamlab.controller;

import by.epamlab.dto.UserDto;
import by.epamlab.model.beans.User;
import by.epamlab.service.UserService;
import by.epamlab.validation.user.EmailExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static by.epamlab.constants.Constants.*;


@Controller
public class RegistrationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService service;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

//    if not to delete @InitBinder then Spring validation check doesn't perform
//    @InitBinder
//    private void initBinder(WebDataBinder binder){
//        binder.setValidator(validator);
//    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult bindingResult,
             WebRequest request,
             Errors errors) {

        validator.validate(accountDto, errors);

        User registered = new User();
        if (!bindingResult.hasErrors()) {
            registered = createUserAccount(accountDto, bindingResult);
        }
        if (registered == null) {
            bindingResult.rejectValue("email", "message.regError");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("successRegister", "user", accountDto);
        }


    }

    private User createUserAccount(UserDto accountDto, BindingResult bindingResult) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
            LOGGER.info(NEW_USER_REGISTERED + accountDto.getUsername());
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }






}
