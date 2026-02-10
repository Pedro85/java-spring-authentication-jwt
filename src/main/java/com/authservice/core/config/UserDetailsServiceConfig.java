package com.authservice.core.config;

import com.authservice.core.repository.UserRepository;
import com.authservice.core.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(final UserRepository userRepository) {
        return username -> {
            final Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

            if (!user.isPresent()) {
                throw new UsernameNotFoundException("User not found");
            }

            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
        };
    }
}
