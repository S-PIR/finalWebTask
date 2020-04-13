package by.epamlab.controller;

import by.epamlab.model.beans.MyUserDetails;
import by.epamlab.model.beans.User;
import by.epamlab.model.beans.cart.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

import static by.epamlab.constants.Constants.*;

@SessionAttributes({"currentUser"})
@Controller
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginFailed")
    public String loginError(Model model){
        LOGGER.warn(LOGIN_ATTEMPT_FAILED);
        model.addAttribute("error", "true");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus, HttpSession session, Model model){
        LOGGER.info(LOGGED_OUT_USER + session.getAttribute("currentUser"));
        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/postLogin")
    public String postLogin(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((MyUserDetails) authentication.getPrincipal()).getUserDetails();

        model.addAttribute("currentUser", loggedInUser.getUsername());
        LOGGER.info(LOGGED_IN_USER + loggedInUser.getUsername());
        loggedInUser.getAuthorities().stream().forEach(x -> LOGGER.info(x.getName().toString()));
        session.setAttribute("userId", loggedInUser.getId());
        session.setMaxInactiveInterval(10*60);
        return "redirect:/main";
    }

    private void validatePrinciple(Object principle){
        if (!(principle instanceof MyUserDetails)){
            LOGGER.warn("Principle can't be null!");
            throw new IllegalArgumentException("Principle can't be null!");
        }
    }


}
