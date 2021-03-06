package pl.patryklubik.myweight.logic.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.model.security.UserDetailsImpl;
import pl.patryklubik.myweight.model.security.User;
import pl.patryklubik.myweight.model.security.UserRepository;

import java.util.Optional;

/**
 * Create by Patryk Łubik on 09.09.2021.
 */

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthoritiesService authoritiesService;
    private final static int STANDARD_USER_ID = 1;

    public SecurityUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthoritiesService authoritiesService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authoritiesService = authoritiesService;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return new UserDetailsImpl(user.get(), authoritiesService.getAuthorities(user.get().getRole()));
    }

    public User save(User toCreate) {

        if(userRepository.existsByUsername(toCreate.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nazwa użytkownika jest zajęta");
        }

        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
        toCreate.setActive(true);
        toCreate.setRole(authoritiesService.findRoleByUserId(STANDARD_USER_ID));

        return userRepository.save(toCreate);
    }

    public User getLoggedInUser() {
        return userRepository.getByUsername(getLoggedInUsername());
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
