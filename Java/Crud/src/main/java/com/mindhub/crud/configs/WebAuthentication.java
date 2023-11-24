package com.mindhub.crud.configs;

import com.mindhub.crud.enums.Role;
import com.mindhub.crud.models.Admin;
import com.mindhub.crud.models.Users;
import com.mindhub.crud.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private UsersRepository usersRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputEmail-> {
            Users user = usersRepository.findByEmail(inputEmail).orElseThrow(() -> new UsernameNotFoundException("Unknown user email: " + inputEmail));

            String userRole = "ROLE_" + user.getRole().name();
            return new User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(userRole));
        }).passwordEncoder(passwordEncoder());
    }
}
