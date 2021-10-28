package pl.patryklubik.myweight.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.PersonalDataService;
import pl.patryklubik.myweight.logic.WeightService;
import pl.patryklubik.myweight.model.*;
import pl.patryklubik.myweight.model.dto.BasicWeightDataDto;
import pl.patryklubik.myweight.model.dto.WeightDto;

import javax.validation.Valid;
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
    public String getStarterPage(Model model) {
        boolean bmiLevelCorrect = weightService.isLoggedInUsersBMILevelCorrect();
        model.addAttribute("personalData", personalDataService.getPersonalDataLoggedInUser());
        String nameFieldsOfBmiLevel = "description_of_bmi_level";
        String nameFieldsOfCorrectBmiLevel = "bmi_level_correct";
        String descriptionOfCorrectBmiLevel = "Wartość BMI w prawidłowym zakresie (18,5 - 24,9)";
        String descriptionOfIncorrectBmiLevel = "Wartość BMI nie jest w prawidłowym zakresie (18,5 - 24,9)";

        model.addAttribute(nameFieldsOfCorrectBmiLevel, bmiLevelCorrect);
        if(bmiLevelCorrect) {
            model.addAttribute(nameFieldsOfBmiLevel, descriptionOfCorrectBmiLevel);
        } else {
            model.addAttribute(nameFieldsOfBmiLevel, descriptionOfIncorrectBmiLevel);
        }

        return "starter";
    }

    @GetMapping("add-weight")
    public String getAddWeightPage() {
        return "add-weight";
    }

    @GetMapping("chart")
    public String getChartPage() {
        return "chart";
    }


    @PostMapping("edit-weight")
    public String editWeight(@ModelAttribute("editedWeight") @Valid Weight editedWeight,
                            Model model) {
        try {
            weightService.update(editedWeight);
        } catch (ResponseStatusException e) {
            System.out.println(e.getReason());
        }
        return "redirect:/weight-history";
    }

    @PostMapping("delete-weight")
    public String deleteWeight(int deleteId) {
        try {
            weightService.delete(deleteId);
        } catch (ResponseStatusException e) {
            System.out.println(e.getReason());
        }
        return "redirect:/weight-history";
    }


    @GetMapping("personal-data")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getPersonalDataPage(Model model) {
        model.addAttribute("personalData", personalDataService.getPersonalDataLoggedInUser());

        return "personal-data";
    }

    @GetMapping("weight-history")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD_USER')")
    public String getWeightHistoryPage(Model model) {


                model.addAttribute("editedWeight", new Weight());
        return "weight-history";
    }

    @PostMapping("add-weight")
    public String addWeight(@ModelAttribute("weight") @Valid Weight newWeight,
                                BindingResult bindingResult,
                                Model model) {

        String pageToReturn = "add-weight";
        String errorModelAttributeName = "message_error";
        String successModelAttributeName = "message_success";

        if (bindingResult.hasErrors()) {
            model.addAttribute(errorModelAttributeName, "Wypełnij wszystkie pola");
            return pageToReturn;
        }

        try {
            weightService.save(newWeight);
            model.addAttribute(successModelAttributeName, "Pomiar dodany");
            return pageToReturn;
        } catch (ResponseStatusException e) {
            model.addAttribute(errorModelAttributeName, e.getReason());
            return pageToReturn;
        }
    }

    @PostMapping("save-personal-data")
    public String savePersonalData(@ModelAttribute("personalData")
                                   @Valid PersonalData personalData,
                            BindingResult bindingResult,
                            Model model) {

        String pageToReturn = "personal-data";
        String errorModelAttributeName = "message_error";
        String successModelAttributeName = "message_success";

        if (bindingResult.hasErrors()) {
            model.addAttribute(errorModelAttributeName, "Wypełnij wszystkie pola");
            return pageToReturn;
        }

        try {
            personalDataService.save(personalData);
            model.addAttribute(successModelAttributeName, "Pomiar dodany");
            return pageToReturn;
        } catch (ResponseStatusException e) {
            model.addAttribute(errorModelAttributeName, e.getReason());
            return pageToReturn;
        }
    }

//    @ModelAttribute("personalData")
//    PersonalData getPersonalDataLoggedInUser() {
//        return personalDataService.getPersonalDataLoggedInUser();
//    }

    @ModelAttribute("basicWeightData")
    BasicWeightDataDto getWeightDataLoggedInUser() {
        return weightService.getBasicWeightDataLoggedInUser();
    }

    @ModelAttribute("weightHistoryData")
    List<WeightDto> getWeightHistoryDataLoggedInUser() {
        return weightService.getWeightHistoryDataLoggedInUser();
    }
}