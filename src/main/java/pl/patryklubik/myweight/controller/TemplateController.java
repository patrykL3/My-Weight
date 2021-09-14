package pl.patryklubik.myweight.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Create by Patryk Łubik on 14.09.2021.
 */

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("")
    public String redirect() {
        return "redirect:/menu";
    }

//    @GetMapping("registration")
//    public String registration(Model model) {
//        model.addAttribute("user", new User());
//        return "registration";
//    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("personal_data")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getPersonalData() {
        return "personal_data";
    }

    @GetMapping("weight_history")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getWeightHistory() {
        return "weight_history";
    }
}