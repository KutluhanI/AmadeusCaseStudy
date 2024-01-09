package amadeus.flight.Security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{

    private static final String[] AUTH_WHITELIST = {
            "/h2-console",
            "/h2-console/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html"
    };

    private final PasswordEncoder passwordEncoder;

    private final UserAuthService userAuthService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userAuthService);

        return authenticationProvider;
    }

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(customizer -> customizer.requestMatchers(AUTH_WHITELIST).anonymous()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().hasAnyRole("ADMIN", "USER"))
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(((request, response, accessDeniedException)
                                -> response.setStatus(HttpServletResponse.SC_FORBIDDEN)))
                        .authenticationEntryPoint((request, response, authException) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
                .formLogin(customizer -> customizer
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN)))
                .sessionManagement(customizer -> customizer.invalidSessionStrategy((request, response) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
                .logout(customizer -> customizer.logoutUrl("/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
     */
}
