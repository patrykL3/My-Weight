package pl.patryklubik.myweight.logic;

import pl.patryklubik.myweight.model.PersonalData;


/**
 * Create by Patryk Łubik on 11.11.2021.
 */


public interface PersonalDataService {

    PersonalData getPersonalDataLoggedInUser();
    PersonalData save(PersonalData personalData);
    boolean isPersonalDataExists();
}
