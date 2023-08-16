package com.SpringWebApplication.SpringWebApplicatoin.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or database
    // In memory
//    InMemoryUserDetailsManager
//    InMemoryUserDetailsManager(UserDetails... users) accepts as many as user
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManger(){
        UserDetails userDetails = createNewUser("masoom", "dummy");
        UserDetails userDetails1 = createNewUser("ruby", "dummy");
        return new InMemoryUserDetailsManager(userDetails,userDetails1 );
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> userpasswordEncoder=
                input -> passwordEncoder().encode(input);
        UserDetails userDetails =   User.builder()
                .passwordEncoder(userpasswordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean// Custom Encoder
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}


