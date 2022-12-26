package com.dmit.controller.user;

import com.dmit.dto.user.UserLoginDto;
import com.dmit.service.user.UserService;
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
    @Autowired
    UserService userService;

    @GetMapping("user-login")
    public String loginUserForm(Model model) {
        model.addAttribute("user", new UserLoginDto());

        return "user/user_login";
    }

    @PostMapping(value = "user-login")
    public String registerUser(@Valid @ModelAttribute("user") UserLoginDto userLoginDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "user/add_user";

        return "redirect:/";
    }
}
