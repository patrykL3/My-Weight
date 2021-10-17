package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.Weight;
import pl.patryklubik.myweight.model.WeightRepository;

import java.util.Date;


@Repository
interface SqlWeightRepository extends WeightRepository, JpaRepository<Weight, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT value FROM weights \n" +
                "WHERE  user_id=:id and date=(\n" +
                    "SELECT MAX(date) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id) " +
                    "ORDER BY id DESC LIMIT 1;")
    float getUserCurrentWeight(@Param("id")Integer id);

    @Query(nativeQuery = true, value = "select max(value) from weights where user_id=:id")
    float getUserMaxWeight(@Param("id")Integer id);

    @Query(nativeQuery = true, value = "select min(value) from weights where user_id=:id")
    float getUserMinWeight(@Param("id")Integer id);


    @Query(nativeQuery = true, value =
            "SELECT MAX(date) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id")
    Date getUserCurrentWeightDate(@Param("id")Integer id);

    @Query(nativeQuery = true, value =
            "SELECT date FROM weights \n" +
                    "WHERE  user_id=:id and value=(\n" +
                    "SELECT MAX(value) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id) " +
                    "fetch first 1 rows only;")
    Date getUserMaxWeightDate(@Param("id")Integer id);

    @Query(nativeQuery = true, value =
            "SELECT date FROM weights \n" +
                    "WHERE  user_id=:id and value=(\n" +
                    "SELECT MIN(value) \n" +
                    "FROM weights \n" +
                    "WHERE user_id=:id) " +
                    "fetch first 1 rows only;")
    Date getUserMinWeightDate(@Param("id")Integer id);

}
