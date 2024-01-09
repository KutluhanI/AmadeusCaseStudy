package amadeus.flight.services;

import amadeus.flight.classes.Role;

import java.util.List;

public interface RoleServiceInt {
    public void checkAndCreateRoles(List<String> roleNames);

    public Role findByName(String name);
}
