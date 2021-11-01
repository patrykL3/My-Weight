package pl.patryklubik.myweight.model;

import pl.patryklubik.myweight.model.security.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface WeightRepository {

    float getUserCurrentWeight(Integer userId);

    float getUserMaxWeight(Integer userId);

    float getUserMinWeight(Integer userId);

    List<Weight> findByUser(User user);

    Date getUserCurrentWeightDate(Integer userId);

    Date getUserMaxWeightDate(Integer userId);

    Date getUserMinWeightDate(Integer userId);

    Weight save(Weight entity);

    Optional< Weight> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}