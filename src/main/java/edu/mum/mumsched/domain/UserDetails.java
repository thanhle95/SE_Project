package edu.mum.mumsched.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserDetails {
    public Collection<? extends GrantedAuthority> getAuthorities();
    public String getPassword();
    public String getUsername();
    public boolean isAccountNonExpired();
    public boolean isAccountNonLocked();
    public boolean isCredentialsNonExpired();
    public boolean isEnabled();
}
