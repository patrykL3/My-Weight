package pl.patryklubik.myweight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.patryklubik.myweight.model.ThymeleafAttributes;


/**
 * Create by Patryk Łubik on 14.09.2021.
 */

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String redirect() {
        return "redirect:/starter";
    }

    @GetMapping("login")
    public String getLoginPage(Model model, boolean error) {
        model.addAttribute(ThymeleafAttributes.ERROR.getName(), error);
        return "login";
    }
}