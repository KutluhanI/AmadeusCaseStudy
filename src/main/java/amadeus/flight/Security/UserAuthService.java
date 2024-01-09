package amadeus.flight.Security;


import amadeus.flight.classes.User;
import amadeus.flight.services.UserService;
import amadeus.flight.services.UserServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthService implements UserDetailsService {

    private final UserServiceInt userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new UserAuthDetails(user);
    }
}
