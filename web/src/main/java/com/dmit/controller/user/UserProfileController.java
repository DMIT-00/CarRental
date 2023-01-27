package com.dmit.controller.user;

import com.dmit.dto.user.UserResponseDto;
import com.dmit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileController {
    private final UserService userService;

    @GetMapping("user-profile")
    public String userProfile(Model model) {
        UserResponseDto userResponseDto = userService.findCurrentUser();

        model.addAttribute("user", userResponseDto);
        return "user/user_profile";
    }
}
