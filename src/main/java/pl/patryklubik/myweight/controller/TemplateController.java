package pl.patryklubik.myweight.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.patryklubik.myweight.logic.PersonalDataService;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.User;


/**
 * Create by Patryk Łubik on 14.09.2021.
 */

@Controller
@RequestMapping("/")
public class TemplateController {

    private final PersonalDataService personalDataService;

    public TemplateController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping("")
    public String redirect() {
        return "redirect:/starter";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("starter")
    public String getStarterPage() {
        return "starter";
    }

    @GetMapping("personal-data")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getPersonalDataPage() {
        return "personal-data";
    }

    @GetMapping("weight-history")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getWeightHistoryPage() {
        return "weight-history";
    }


    @ModelAttribute("personalData")
    PersonalData getPersonalDataLoggedInUser() {
        return personalDataService.getPersonalDataLoggedInUser();
    }
}