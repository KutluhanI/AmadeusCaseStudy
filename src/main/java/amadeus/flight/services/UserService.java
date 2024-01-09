package amadeus.flight.services;

import amadeus.flight.Exceptions.FlightNotFound;
import amadeus.flight.Security.Constants;
import amadeus.flight.classes.Role;
import amadeus.flight.classes.User;
import amadeus.flight.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInt{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleServiceInt roleServiceInt;

    @Override
    public User findByUsername(String username) {
        Objects.requireNonNull(username, "username object is null");
        return userRepository.findUserByUsername(username).orElseThrow(FlightNotFound::new);
    }

    @Transactional
    @Override
    public User createNewUser(User newUser) {
        Objects.requireNonNull(newUser,"newUser is missing");
        userRepository.save(newUser);
        return newUser;
    }

    @Transactional
    @Override
    public void checkAndCreateAdminUser() {
        userRepository.findUserByUsername("sysadmin").orElseGet(() -> {
            Role role = roleServiceInt.findByName(Constants.Roles.ADMIN);

            final User user = User.builder()
                    .enabled(Boolean.TRUE)
                    .username("sysadmin")
                    .role(role)
                    .password(passwordEncoder.encode("Admin123"))
                    .build();

            role.getUsers().add(user);
            return createNewUser(user);
        });

        userRepository.findUserByUsername("sysuser").orElseGet(() -> {
            Role role = roleServiceInt.findByName(Constants.Roles.USER);

            final User user = User.builder()
                    .enabled(Boolean.TRUE)
                    .username("sysuser")
                    .role(role)
                    .password(passwordEncoder.encode("user123"))
                    .build();

            role.getUsers().add(user);
            return createNewUser(user);
        });
    }
}
