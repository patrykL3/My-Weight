package pl.patryklubik.myweight.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.patryklubik.myweight.logic.PersonalDataService;
import pl.patryklubik.myweight.logic.WeightService;
import pl.patryklubik.myweight.model.BasicWeightDataDto;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.Weight;

import java.util.List;


/**
 * Create by Patryk Łubik on 02.10.2021.
 */

@Controller
@RequestMapping("/")
public class LoggedInUserController {

    private final PersonalDataService personalDataService;
    private final WeightService weightService;

    public LoggedInUserController(PersonalDataService personalDataService, WeightService weightService) {
        this.personalDataService = personalDataService;
        this.weightService = weightService;
    }

    @GetMapping("starter")
    public String getStarterPage() {
        return "starter";
    }

    @GetMapping("add-weight")
    public String getAddWeightPage() {
        return "add-weight";
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

    @ModelAttribute("basicWeightData")
    BasicWeightDataDto getWeightDataLoggedInUser() {
        return weightService.getBasicWeightDataLoggedInUser();
    }

    @ModelAttribute("weightHistoryData")
    List<Weight> getWeightHistoryDataLoggedInUser() {
        return weightService.getWeightHistoryDataLoggedInUser();
    }
}