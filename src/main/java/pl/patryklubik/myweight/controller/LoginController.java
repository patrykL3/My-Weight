package pl.patryklubik.myweight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getLoginPage() {
        return "login";
    }
}