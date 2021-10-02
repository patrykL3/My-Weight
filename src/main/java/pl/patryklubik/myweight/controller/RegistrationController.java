package pl.patryklubik.myweight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.SecurityUserService;
import pl.patryklubik.myweight.model.User;

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
    public String createAccount(@ModelAttribute("user") @Valid User toCreate,
    BindingResult bindingResult,
    Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Fill in all fields");
            return "registration";
        }

        try {
            securityUserService.save(toCreate);
            return "login";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", e.getReason());
            return "registration";
        }
    }

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
}
