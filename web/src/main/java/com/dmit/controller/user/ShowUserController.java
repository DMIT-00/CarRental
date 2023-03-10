package com.dmit.controller.user;

import com.dmit.dto.user.UserResponseDto;
import com.dmit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShowUserController {
    private final UserService userService;

    @GetMapping("/user-show/{userId}")
    String showUser(@PathVariable(required = true) UUID userId, Model model) {
        UserResponseDto userResponseDto = userService.findUserById(userId);

        model.addAttribute("user", userResponseDto);

        return "user/show_user";
    }
}
