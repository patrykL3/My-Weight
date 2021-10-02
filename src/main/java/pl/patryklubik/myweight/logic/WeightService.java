package pl.patryklubik.myweight.logic;

import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.*;

import java.util.List;


/**
 * Create by Patryk Łubik on 20.09.2021.
 */

@Service
public class WeightService {

    private final WeightRepository weightRepository;
    private final PersonalDataRepository personalDataRepository;
    private final SecurityUserService securityUserService;


    public WeightService(WeightRepository weightRepository, PersonalDataRepository personalDataRepository, SecurityUserService securityUserService) {
        this.weightRepository = weightRepository;
        this.personalDataRepository = personalDataRepository;
        this.securityUserService = securityUserService;
    }


    public BasicWeightDataDto getBasicWeightDataLoggedInUser() {
        int loggedInUserId = securityUserService.getLoggedInUser().getId();
        float currentWeight = getCurrentWeightUser(loggedInUserId);
        float minWeight = getMinWeightUser(loggedInUserId);
        float maxWeight = getMaxWeightUser(loggedInUserId);
        float bmi = calculateBMILoggedInUser();

        return new BasicWeightDataDto(currentWeight, minWeight, maxWeight, bmi);
    }

    public List<Weight> getWeightHistoryDataLoggedInUser() {

        List<Weight> a = weightRepository.findByUser(securityUserService.getLoggedInUser());


        return weightRepository.findByUser(securityUserService.getLoggedInUser());
    }

    private float calculateBMILoggedInUser() {
        User loggedInUser = securityUserService.getLoggedInUser();
        PersonalData personalDataLoggedInUser = personalDataRepository.findByUser(loggedInUser);
        float currentWeight = getCurrentWeightUser(loggedInUser.getId());
        int height = personalDataLoggedInUser.getHeight();

        return currentWeight*currentWeight/height;
    }

    private float getCurrentWeightUser(int userId) {

        return weightRepository.getUserCurrentWeight(userId);
    }

    private float getMaxWeightUser(int userId) {

        return weightRepository.getUserMaxWeight(userId);
    }

    private float getMinWeightUser(int userId) {

        return weightRepository.getUserMinWeight(userId);
    }
}
