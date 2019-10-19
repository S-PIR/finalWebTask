package by.epamlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {
    @GetMapping("/")
    public String home(Model model){
        String str = "User";
        model.addAttribute("user", str);
        return "home";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        System.out.println("login controller");
        return "main";
    }
    @PostMapping("/main")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        return "main";
    }


}
