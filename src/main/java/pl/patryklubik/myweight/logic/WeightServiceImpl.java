package pl.patryklubik.myweight.logic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.security.SecurityUserService;
import pl.patryklubik.myweight.model.*;
import pl.patryklubik.myweight.model.dto.BasicWeightDataDto;
import pl.patryklubik.myweight.model.dto.WeightDto;
import pl.patryklubik.myweight.model.security.User;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Create by Patryk Łubik on 20.09.2021.
 */

@Service
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;
    private final PersonalDataRepository personalDataRepository;
    private final SecurityUserService securityUserService;

    private final static float LOWER_LIMIT_OF_CORRECT_BMI = 18.5f;
    private final static float UPPER_LIMIT_OF_CORRECT_BMI = 24.9f;

    public WeightServiceImpl(WeightRepository weightRepository, PersonalDataRepository personalDataRepository, SecurityUserService securityUserService) {
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

        Date currentWeightDate = getCurrentWeightDateUser(loggedInUserId);
        Date minWeightDate = getMinWeightDateUser(loggedInUserId);
        Date maxWeightDate = getMaxWeightDateUser(loggedInUserId);

        return new BasicWeightDataDto(currentWeight, currentWeightDate, minWeight, minWeightDate, maxWeight,
                maxWeightDate, bmi);
    }

    public List<WeightDto> getWeightHistoryDataLoggedInUser() {

        return weightRepository.findByUser(securityUserService.getLoggedInUser())
                .stream().map(weight -> new WeightDto(weight.getId(), weight.getValue(), weight.getDate()))
                .sorted(Comparator.comparing(WeightDto::getDate))
                .collect(Collectors.toList());
    }

    public boolean isLoggedInUsersBMILevelCorrect() {
        return calculateBMILoggedInUser() > LOWER_LIMIT_OF_CORRECT_BMI && calculateBMILoggedInUser() < UPPER_LIMIT_OF_CORRECT_BMI;
    }

    private float calculateBMILoggedInUser() {
        User loggedInUser = securityUserService.getLoggedInUser();
        PersonalData personalDataLoggedInUser = personalDataRepository.findByUser(loggedInUser);
        float currentWeight = getCurrentWeightUser(loggedInUser.getId());
        float height = personalDataLoggedInUser.getHeight();

        float bmi = currentWeight/(height*height/10000);

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


    private Date getCurrentWeightDateUser(int userId) {

        return weightRepository.getUserCurrentWeightDate(userId);
    }

    private Date getMaxWeightDateUser(int userId) {

        return weightRepository.getUserMaxWeightDate(userId);
    }

    private Date getMinWeightDateUser(int userId) {

        return weightRepository.getUserMinWeightDate(userId);
    }

    public Weight save(Weight newWeight) {

        newWeight.setUser(securityUserService.getLoggedInUser());

        return weightRepository.save(newWeight);
    }

    public Weight update(Weight editedWeight) {

        int editWeightId = editedWeight.getId();
        if(!weightRepository.existsById(editWeightId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

        weightRepository.findById(editWeightId)
                .ifPresent(weightFromDb -> {
                    weightFromDb.update(editedWeight);
                    weightRepository.save(weightFromDb);
                });
        return editedWeight;
    }

    public void delete(int id) {
        if(!weightRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        weightRepository.deleteById(id);
    }

    public boolean isWeightDataExists() {
        return !weightRepository.findByUser(securityUserService.getLoggedInUser()).isEmpty();
    }
}