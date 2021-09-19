package pl.patryklubik.myweight.logic;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;
import pl.patryklubik.myweight.model.User;
import pl.patryklubik.myweight.model.UserRepository;


/**
 * Create by Patryk Łubik on 18.09.2021.
 */

@Service
public class PersonalDataService {

    private final PersonalDataRepository personalDataRepository;
    private final UserRepository userRepository;

    public PersonalDataService(PersonalDataRepository personalDataRepository, UserRepository userRepository) {
        this.personalDataRepository = personalDataRepository;
        this.userRepository = userRepository;
    }


    public PersonalData getPersonalDataLoggedInUser() {
        User loggedInUser = userRepository.getByUsername(getLoggedInUsername());

        return personalDataRepository.findByUser(loggedInUser);
    }

    private String getLoggedInUsername() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}
