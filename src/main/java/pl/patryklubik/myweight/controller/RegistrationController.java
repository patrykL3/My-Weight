package pl.patryklubik.myweight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.security.SecurityUserService;
import pl.patryklubik.myweight.model.ThymeleafAttributes;
import pl.patryklubik.myweight.model.security.User;

import javax.validation.Valid;

/**
 * Create by Patryk Łubik on 16.09.2021.
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final SecurityUserService securityUserService;

    public RegistrationController(SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
    }


    @PostMapping
    public String createAccount(@Valid User toCreate,
    BindingResult bindingResult,
    Model model) {
        model.addAttribute(ThymeleafAttributes.USER.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute(ThymeleafAttributes.MESSAGE.getName(), "Uzupełnij wszystkie pola");
            return "registration";
        }

        try {
            securityUserService.save(toCreate);
            return "login";
        } catch (ResponseStatusException e) {
            model.addAttribute(ThymeleafAttributes.MESSAGE.getName(), e.getReason());
            return "registration";
        }
    }

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute(ThymeleafAttributes.USER.getName(), new User());
        return "registration";
    }
}