package com.dmit.security;

import com.dmit.dto.user.UserAuthenticationDto;
import com.dmit.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthenticationService implements UserDetailsService {
    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserAuthenticationDto user = userAuthenticationService.findUserByUsername(username);

            if (user == null)
                throw new UsernameNotFoundException("User not found: " + username);

            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    true, true, true, !user.isLocked(),
                    user.getRoles()
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found: " + username, e);
        }
    }
}
