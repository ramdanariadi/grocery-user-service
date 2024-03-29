package id.grocery.tunas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import id.grocery.tunas.config.security.filter.MyAuthorizationFilter;
import id.grocery.tunas.config.security.filter.MyCustomAuthenticationFilter;
import id.grocery.tunas.user.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MyCustomAuthenticationFilter customAuthenticationFilter = new MyCustomAuthenticationFilter(authenticationManagerBean(), userService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/user/token").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/product/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/product/top").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/product/recommendation").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/wishlist").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/category").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/user/register/**").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/**").authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new MyAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
