package com.dmit.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLogInController {

    @GetMapping("user-login")
    public String loginUserForm() {
        return "user/user_login";
    }
}
