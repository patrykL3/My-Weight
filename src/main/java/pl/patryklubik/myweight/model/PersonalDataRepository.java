package pl.patryklubik.myweight.model;


import pl.patryklubik.myweight.model.security.User;

import java.util.Optional;


public interface PersonalDataRepository {

    PersonalData findByUser(User user);
    PersonalData save(PersonalData personalData);
    Optional< PersonalData> findById(Integer id);
    boolean existsById(Integer id);
}
