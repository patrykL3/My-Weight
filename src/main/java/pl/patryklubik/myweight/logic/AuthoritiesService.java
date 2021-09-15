package pl.patryklubik.myweight.logic;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.patryklubik.myweight.model.Permission;
import pl.patryklubik.myweight.model.PermissionRepository;
import pl.patryklubik.myweight.model.Role;
import pl.patryklubik.myweight.model.RoleRepository;

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


    public Set<SimpleGrantedAuthority> getAuthorities(Role role) {

        Set<Permission> permissionsFromDb = permissionRepository.findByRoles(role);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = permissionsFromDb.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return simpleGrantedAuthorities;
    }

    public Role findRoleByUserId(int userId) {

        return roleRepository.findById(userId).get();
    }
}
