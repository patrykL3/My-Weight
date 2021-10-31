package pl.patryklubik.myweight.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


/**
 * Create by Patryk Łubik on 03.09.2021.
 */

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private Set<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(User user, Set<SimpleGrantedAuthority> authorities) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return active;
    }
}
