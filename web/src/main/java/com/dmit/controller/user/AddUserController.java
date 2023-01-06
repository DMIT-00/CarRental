package com.dmit.controller.user;

import com.dmit.dto.user.UserDetailDto;
import com.dmit.dto.user.UserRequestDto;
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
public class AddUserController {
    @Autowired
    UserService userService;

    @GetMapping("add-user")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new UserRequestDto(new UserDetailDto()));

        return "user/add_user";
    }

    @PostMapping(value = "add-user")
    public String registerUser(@Valid @ModelAttribute("user") UserRequestDto userRequestDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "user/add_user";

        userService.registerUser(userRequestDto);

        return "redirect:/";
    }
}
