package pl.patryklubik.myweight.logic.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.security.Permission;
import pl.patryklubik.myweight.model.security.PermissionRepository;
import pl.patryklubik.myweight.model.security.Role;
import pl.patryklubik.myweight.model.security.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by Patryk Łubik on 09.09.2021.
 */

@Service
public class AuthoritiesService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public AuthoritiesService(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }


    Set<SimpleGrantedAuthority> getAuthorities(Role role) {

        Set<Permission> permissionsFromDb = permissionRepository.findByRoles(role);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = permissionsFromDb.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return simpleGrantedAuthorities;
    }

    Role findRoleByUserId(int userId) {

        return roleRepository.findById(userId).get();
    }
}