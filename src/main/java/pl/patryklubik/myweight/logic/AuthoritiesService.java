package pl.patryklubik.myweight.logic;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.Permission;
import pl.patryklubik.myweight.model.PermissionRepository;
import pl.patryklubik.myweight.model.Role;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by Patryk Łubik on 09.09.2021.
 */

@Service
public class AuthoritiesService {


    PermissionRepository permissionRepository;

    public AuthoritiesService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(Role role) {

        Set<Permission> permissionsFromDb = permissionRepository.findByRoles(role);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = permissionsFromDb.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}
