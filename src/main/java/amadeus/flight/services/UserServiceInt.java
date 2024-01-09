package amadeus.flight.services;

import amadeus.flight.classes.User;

public interface UserServiceInt {
    User findByUsername(String username);

    void checkAndCreateAdminUser();

    User createNewUser(User newUser);
}
