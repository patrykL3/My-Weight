package pl.patryklubik.myweight.logic;

import pl.patryklubik.myweight.model.Weight;
import pl.patryklubik.myweight.model.dto.BasicWeightDataDto;
import pl.patryklubik.myweight.model.dto.WeightDto;

import java.util.List;


/**
 * Create by Patryk Łubik on 11.11.2021.
 */

public interface WeightService {

    BasicWeightDataDto getBasicWeightDataLoggedInUser();
    List<WeightDto> getWeightHistoryDataLoggedInUser();
    boolean isLoggedInUsersBMILevelCorrect();
    Weight save(Weight newWeight);
    Weight update(Weight editedWeight);
    void delete(int id);
    boolean isWeightDataExists();
}