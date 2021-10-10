package pl.patryklubik.myweight.logic;

import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        float currentWeight = getCurrentWeightUser(loggedInUserId);
        float minWeight = getMinWeightUser(loggedInUserId);
        float maxWeight = getMaxWeightUser(loggedInUserId);
        float bmi = calculateBMILoggedInUser();

        String currentWeightDate = getCurrentWeightDateUser(loggedInUserId).format(formatters);
        String minWeightDate = getMinWeightDateUser(loggedInUserId).format(formatters);
        String maxWeightDate = getMaxWeightDateUser(loggedInUserId).format(formatters);

        return new BasicWeightDataDto(currentWeight, currentWeightDate, minWeight, minWeightDate, maxWeight,
                maxWeightDate, bmi);
    }

    public List<Weight> getWeightHistoryDataLoggedInUser() {

        return weightRepository.findByUser(securityUserService.getLoggedInUser());
    }

    private float calculateBMILoggedInUser() {
        User loggedInUser = securityUserService.getLoggedInUser();
        PersonalData personalDataLoggedInUser = personalDataRepository.findByUser(loggedInUser);
        float currentWeight = getCurrentWeightUser(loggedInUser.getId());
        int height = personalDataLoggedInUser.getHeight();

        float bmi = currentWeight*currentWeight/height;

        return Math.round(bmi * 10) / 10f;
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


    private LocalDateTime getCurrentWeightDateUser(int userId) {

        return weightRepository.getUserCurrentWeightDate(userId);
    }

    private LocalDateTime getMaxWeightDateUser(int userId) {

        return weightRepository.getUserMaxWeightDate(userId);
    }

    private LocalDateTime getMinWeightDateUser(int userId) {

        return weightRepository.getUserMinWeightDate(userId);
    }
}
