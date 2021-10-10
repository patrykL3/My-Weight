package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.Weight;
import pl.patryklubik.myweight.model.WeightRepository;

import java.time.LocalDateTime;


@Repository
interface SqlWeightRepository extends WeightRepository, JpaRepository<Weight, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT weight FROM weights \n" +
                "WHERE  user_id=:id and date=(\n" +
                    "SELECT MAX(date) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id);")
    float getUserCurrentWeight(@Param("id")Integer id);

    @Query(nativeQuery = true, value = "select max(weight) from weights where user_id=:id")
    float getUserMaxWeight(@Param("id")Integer id);

    @Query(nativeQuery = true, value = "select min(weight) from weights where user_id=:id")
    float getUserMinWeight(@Param("id")Integer id);


    @Query(nativeQuery = true, value =
            "SELECT MAX(date) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id")
    LocalDateTime getUserCurrentWeightDate(@Param("id")Integer id);

    @Query(nativeQuery = true, value =
            "SELECT date FROM weights \n" +
                    "WHERE  user_id=:id and weight=(\n" +
                    "SELECT MAX(weight) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id);")
    LocalDateTime getUserMaxWeightDate(@Param("id")Integer id);

    @Query(nativeQuery = true, value =
            "SELECT date FROM weights \n" +
                    "WHERE  user_id=:id and weight=(\n" +
                    "SELECT MIN(weight) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id);")
    LocalDateTime getUserMinWeightDate(@Param("id")Integer id);

}
