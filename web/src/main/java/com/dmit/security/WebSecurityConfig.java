package com.dmit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeHttpRequests((authorize ) -> authorize
                        .antMatchers("/").permitAll()
                        .antMatchers("/user-add").permitAll()
                        .antMatchers("/user-login").permitAll()
                        .antMatchers("/user-logout").permitAll()
                        .antMatchers("/car-list").permitAll()
                        .antMatchers("/car-show/**").permitAll()
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/img/**").permitAll()
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
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

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth,
                                        @Qualifier("authService") AuthenticationService service) throws Exception {
        auth.userDetailsService(service);
    }
}