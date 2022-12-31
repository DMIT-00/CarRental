package com.dmit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@ComponentScan(basePackages = {"com.dmit.security"})
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeHttpRequests((authorize ) -> authorize
                        .antMatchers("/").permitAll()
                        .antMatchers("/add-user").permitAll()
                        .antMatchers("/user-login").permitAll()
                        .antMatchers("/user-logout").permitAll()
                        .antMatchers("/css/**").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin()
                .loginPage("/user-login")
                .loginProcessingUrl("/user-login")
                .defaultSuccessUrl("/", false)

                .and()

                .logout()
                .logoutUrl("/user-logout")
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("root")
                .password("root")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}