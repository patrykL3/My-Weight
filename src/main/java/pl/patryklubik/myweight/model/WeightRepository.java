package pl.patryklubik.myweight.model;

import java.time.LocalDateTime;
import java.util.List;


public interface WeightRepository {

    float getUserCurrentWeight(Integer userId);

    float getUserMaxWeight(Integer userId);

    float getUserMinWeight(Integer userId);

    List<Weight> findByUser(User user);

    LocalDateTime getUserCurrentWeightDate(Integer userId);

    LocalDateTime getUserMaxWeightDate(Integer userId);

    LocalDateTime getUserMinWeightDate(Integer userId);
}
