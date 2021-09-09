package pl.patryklubik.myweight.logic;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.patryklubik.myweight.model.Permission;
import pl.patryklubik.myweight.model.PermissionRepository;
import pl.patryklubik.myweight.model.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by Patryk Łubik on 09.09.2021.
 */

public class AuthoritiesService {


    PermissionRepository permissionRepository;

    public AuthoritiesService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(Role role) {

        List<Permission> permissionsFromDb = permissionRepository.findByRole(role);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = permissionsFromDb.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}
