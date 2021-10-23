package pl.patryklubik.myweight.logic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;
import pl.patryklubik.myweight.model.User;


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

        int loggedUserId = getPersonalDataLoggedInUser().getId();

        if(!personalDataRepository.existsById(loggedUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

         personalDataRepository.findById(loggedUserId)
                .ifPresent(personalDataFromDb -> {
                    personalDataFromDb.update(personalData);
                    personalDataRepository.save(personalDataFromDb);
                });
        return personalData;
    }
}
