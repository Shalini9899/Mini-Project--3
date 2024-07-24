package com.Captone.Bus_Ticket_Booking_Application.Config;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Passenger;
import com.Captone.Bus_Ticket_Booking_Application.Repository.PassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    PassangerRepository passanger_repository;
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        List<Passenger> passengerEntities = passanger_repository.findAll();

        List<UserDetails> allUsers = new ArrayList<>();

        for (Passenger p : passengerEntities) {
            allUsers.add(User.withUsername(p.getEmail()).password(encoder.encode(p.getLogin_password())).roles("USER")
                    .build());
        }
        return new InMemoryUserDetailsManager(allUsers);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // custom login page
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated()
                )
                .build();
    }


}
