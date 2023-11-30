package cinema.security;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails Catalin = User.builder()
                .username("Catalin")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();
        UserDetails Suzana = User.builder()
                .username("Suzana")
                .password("{noop}test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(Catalin, Suzana);
    }

     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // Specify the custom queries to match your table structure
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT u.username, a.authority FROM users u JOIN authorities a ON u.user_id = a.user_id WHERE u.username = ?");

        return manager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users").anonymous()
                        .requestMatchers(HttpMethod.PUT, "/api/users").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/movies").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/movies/**").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/movies").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/movies").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/movies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies/tickets").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies/tickets/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies/buyTicket/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/movies/tickets/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/movies/buyTicket/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies/name/**").hasAnyAuthority("USER")


        );
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }



}

