package com.dmit.service;

import com.dmit.dto.user.UserAuthenticationDto;

public interface UserAuthenticationService {
    UserAuthenticationDto findUserByUsername(String username);
}
