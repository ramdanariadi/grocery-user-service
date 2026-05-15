package id.grocery.tunas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import id.grocery.tunas.config.security.filter.MyAuthorizationFilter;
import id.grocery.tunas.config.security.filter.MyCustomAuthenticationFilter;
import id.grocery.tunas.user.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        MyCustomAuthenticationFilter customAuthenticationFilter = new MyCustomAuthenticationFilter(authenticationManager, userService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/user/token").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/product/top").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/product/recommendation").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/wishlist").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/category").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/register/**").permitAll()
                .requestMatchers("/api/v1/**").authenticated()
        );
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new MyAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
