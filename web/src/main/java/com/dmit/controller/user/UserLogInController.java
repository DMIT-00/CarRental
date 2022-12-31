package com.dmit.controller.user;

import com.dmit.dto.user.UserLoginDto;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserLogInController {

    @GetMapping("user-login")
    public String loginUserForm() {
        return "user/user_login";
    }
}
