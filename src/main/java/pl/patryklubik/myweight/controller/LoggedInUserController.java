package pl.patryklubik.myweight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.PersonalDataService;
import pl.patryklubik.myweight.logic.WeightService;
import pl.patryklubik.myweight.model.*;

import javax.validation.Valid;


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
        String descriptionOfCorrectBmiLevel = "Wartość BMI w prawidłowym zakresie (18,5 - 24,9)";
        String descriptionOfIncorrectBmiLevel = "Wartość BMI nie jest w prawidłowym zakresie (18,5 - 24,9)";

        if(personalDataService.isPersonalDataExists() && weightService.isWeightDataExists()) {
            boolean bmiLevelCorrect = weightService.isLoggedInUsersBMILevelCorrect();
            model.addAttribute(ThymeleafAttributes.PERSONAL_DATA.getName(), personalDataService.getPersonalDataLoggedInUser());
            model.addAttribute(ThymeleafAttributes.BASIC_WEIGHT_DATA.getName(), weightService.getBasicWeightDataLoggedInUser());
            model.addAttribute(ThymeleafAttributes.BMI_LEVEL_CORRECT.getName(), bmiLevelCorrect);

            if(bmiLevelCorrect) {
                model.addAttribute(ThymeleafAttributes.DESCRIPTION_OF_BMI_LEVEL.getName(), descriptionOfCorrectBmiLevel);
            } else {
                model.addAttribute(ThymeleafAttributes.DESCRIPTION_OF_BMI_LEVEL.getName(), descriptionOfIncorrectBmiLevel);
            }
        }
        return "starter";
    }

    @GetMapping("add-weight")
    public String getAddWeightPage(Model model) {
        model.addAttribute(ThymeleafAttributes.WEIGHT.getName(), new Weight());

        return "add-weight";
    }

    @PostMapping("add-weight")
    public String addWeight(@Valid Weight weight,
                            BindingResult bindingResult,
                            Model model) {
        String pageToReturn = "add-weight";
        model.addAttribute(ThymeleafAttributes.WEIGHT.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute(ThymeleafAttributes.MESSAGE_ERROR.getName(), "Wypełnij wszystkie pola");
            return pageToReturn;
        }

        try {
            weightService.save(weight);
            model.addAttribute(ThymeleafAttributes.MESSAGE_SUCCESS.getName(), "Pomiar dodany");
            return pageToReturn;
        } catch (ResponseStatusException e) {
            model.addAttribute(ThymeleafAttributes.MESSAGE_ERROR.getName(), e.getReason());
            return pageToReturn;
        }
    }

    @GetMapping("weight-history")
    public String getWeightHistoryPage(Model model) {
        model.addAttribute(ThymeleafAttributes.WEIGHT_HISTORY_DATA.getName(), weightService.getWeightHistoryDataLoggedInUser());
        model.addAttribute(ThymeleafAttributes.EDITED_WEIGHT.getName(), new Weight());

        return "weight-history";
    }

    @PostMapping("edit-weight")
    public String editWeight(@Valid Weight editedWeight, Model model) {
        model.addAttribute(ThymeleafAttributes.EDITED_WEIGHT.getName());
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

    @GetMapping("chart")
    public String getChartPage(Model model) {
        model.addAttribute(ThymeleafAttributes.WEIGHT_HISTORY_DATA.getName(), weightService.getWeightHistoryDataLoggedInUser());

        return "chart";
    }

    @GetMapping("personal-data")
    public String getPersonalDataPage(Model model) {
        PersonalData personalDataLoggedInUser = new PersonalData();

        if (personalDataService.isPersonalDataExists()) {
            personalDataLoggedInUser = personalDataService.getPersonalDataLoggedInUser();
        }
        model.addAttribute(ThymeleafAttributes.PERSONAL_DATA.getName(), personalDataLoggedInUser);

        return "personal-data";
    }

    @PostMapping("save-personal-data")
    public String savePersonalData(@Valid PersonalData personalData,
                                   BindingResult bindingResult,
                                   Model model) {
        model.addAttribute(ThymeleafAttributes.PERSONAL_DATA.getName());
        String pageToReturn = "personal-data";

        if (bindingResult.hasErrors()) {
            model.addAttribute(ThymeleafAttributes.MESSAGE_ERROR.getName(), "Wypełnij wszystkie pola");
            return pageToReturn;
        }

        try {
            personalDataService.save(personalData);
            model.addAttribute(ThymeleafAttributes.MESSAGE_SUCCESS.getName(), "Zapisano dane");
            return pageToReturn;
        } catch (ResponseStatusException e) {
            model.addAttribute(ThymeleafAttributes.MESSAGE_ERROR.getName(), e.getReason());
            return pageToReturn;
        }
    }
}