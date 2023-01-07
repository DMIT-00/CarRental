package com.dmit.controller.user;

import com.dmit.dto.user.UserDto;
import com.dmit.dto.user.UserResponceDto;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@Secured("ROLE_ADMIN")
public class ShowUserController {
    @Autowired
    UserService userService;

    @GetMapping("/user-show/{userId}")
    String showUser(@PathVariable(required = true) UUID userId, Model model) {
        UserResponceDto userResponceDto = userService.findUserById(userId);

        model.addAttribute("user", userResponceDto);

        return "user/show_user";
    }
}
