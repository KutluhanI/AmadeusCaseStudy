package amadeus.flight.Security;

import amadeus.flight.classes.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserAuthDetails implements UserDetails {

    private final User user;

    public UserAuthDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.nonNull(user) && Objects.nonNull(user.getRole())) {
            return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEnabled();
    }
}
