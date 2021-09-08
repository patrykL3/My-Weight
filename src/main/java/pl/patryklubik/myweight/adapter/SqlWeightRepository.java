package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.Weight;
import pl.patryklubik.myweight.model.WeightRepository;

@Repository
interface SqlWeightRepository extends WeightRepository, JpaRepository<Weight, Integer> {
}
