package com.klef.jfsd.springbootclg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.klef.jfsd.springbootclg.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Injection of UserService
    
    private final  UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public SecurityConfiguration(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // BCrypt password encoder bean for encrypting passwords
   

    // DaoAuthenticationProvider bean configuration
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // SecurityFilterChain for configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/registration**").permitAll()   // Permit access to registration pages
                .requestMatchers("/js/**").permitAll()            // Permit access to JavaScript files
                .requestMatchers("/css/**").permitAll()           // Permit access to CSS files
                .requestMatchers("/img/**").permitAll()           // Permit access to image resources
                .anyRequest().authenticated()                    // All other requests need authentication
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        // You must return the SecurityFilterChain object
        return http.build();
    }


    // Configure the authentication manager using the DaoAuthenticationProvider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
