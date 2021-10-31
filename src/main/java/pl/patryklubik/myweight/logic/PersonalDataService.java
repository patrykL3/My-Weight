package pl.patryklubik.myweight.logic;

import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.logic.security.SecurityUserService;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;
import pl.patryklubik.myweight.model.security.User;


/**
 * Create by Patryk Łubik on 18.09.2021.
 */

@Service
public class PersonalDataService {

    private final PersonalDataRepository personalDataRepository;
    private final SecurityUserService securityUserService;

    public PersonalDataService(PersonalDataRepository personalDataRepository, SecurityUserService securityUserService) {
        this.personalDataRepository = personalDataRepository;
        this.securityUserService = securityUserService;
    }


    public PersonalData getPersonalDataLoggedInUser() {
        User loggedInUser = securityUserService.getLoggedInUser();

        return personalDataRepository.findByUser(loggedInUser);
    }

    public PersonalData save(PersonalData personalData) {

        if(isPersonalDataExists()) {
            personalDataRepository.findById(getPersonalDataLoggedInUser().getId())
                    .ifPresent(personalDataFromDb -> {
                        personalDataFromDb.update(personalData);
                        personalDataRepository.save(personalDataFromDb);
                    });
        } else {
            personalData.setUser(securityUserService.getLoggedInUser());
            personalDataRepository.save(personalData);
        }

        return personalData;
    }

    public boolean isPersonalDataExists() {
        return getPersonalDataLoggedInUser() != null;
    }
}
