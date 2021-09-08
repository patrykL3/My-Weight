package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;


@Repository
interface SqlPersonalDataRepository extends PersonalDataRepository, JpaRepository<PersonalData, Integer> {
}
