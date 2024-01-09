package amadeus.flight.Security;

import amadeus.flight.services.RoleServiceInt;
import amadeus.flight.services.UserServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RoleConfig implements ApplicationRunner {

    private final RoleServiceInt roleService;

    private final UserServiceInt userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleService.checkAndCreateRoles(List.of(Constants.Roles.ADMIN, Constants.Roles.USER));
        userService.checkAndCreateAdminUser();
    }
}
