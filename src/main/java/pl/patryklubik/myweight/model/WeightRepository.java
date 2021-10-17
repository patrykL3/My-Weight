package pl.patryklubik.myweight.model;

import java.util.Date;
import java.util.List;


public interface WeightRepository {

    float getUserCurrentWeight(Integer userId);

    float getUserMaxWeight(Integer userId);

    float getUserMinWeight(Integer userId);

    List<Weight> findByUser(User user);

    Date getUserCurrentWeightDate(Integer userId);

    Date getUserMaxWeightDate(Integer userId);

    Date getUserMinWeightDate(Integer userId);

    Weight save(Weight entity);
}
