package pl.patryklubik.myweight.model;

import java.util.List;


public interface WeightRepository {

    float getUserCurrentWeight(Integer userId);

    float getUserMaxWeight(Integer userId);

    float getUserMinWeight(Integer userId);

    List<Weight> findByUser(User user);
}
