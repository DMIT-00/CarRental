package com.dmit.controller.user;

import com.dmit.dto.MessageBox;
import com.dmit.dto.UserRequestFormDto;
import com.dmit.dto.user.UserDetailDto;
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

    @GetMapping("user-add")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new UserRequestFormDto(new UserDetailDto()));

        return "user/add_user";
    }

    @PostMapping(value = "user-add")
    public String registerUser(@Valid @ModelAttribute("user") UserRequestFormDto userRequestDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "user/add_user";

        userService.addUser(userRequestDto);

        model.addAttribute("messageBox",
                new MessageBox("user.success", "user.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
