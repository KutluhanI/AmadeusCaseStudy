package amadeus.flight.services;

import amadeus.flight.Exceptions.FlightNotFound;
import amadeus.flight.classes.Role;
import amadeus.flight.respositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInt {

    private final RoleRepository roleRepository;

    public void checkAndCreateRoles(List<String> roleNames){
        if(CollectionUtils.isEmpty(roleNames)) {
            return;
        }
        roleNames.forEach(roleName ->
            roleRepository.findByName(roleName).orElseGet(() ->
                roleRepository.save(Role.builder().name(roleName).build())));
    }

    @Override
    public Role findByName(String name) {
        Objects.requireNonNull(name, "username is missing");
        return roleRepository.findByName(name).orElseThrow(FlightNotFound::new);
    }
}
