package com.dmit.controller.user;

import com.dmit.dto.MessageBox;
import com.dmit.dto.mapper.UserRequestDtoMapper;
import com.dmit.dto.mapper.UserResponseDtoMapper;
import com.dmit.dto.user.UserRequestDto;
import com.dmit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditUserController {
    private final UserResponseDtoMapper userResponseDtoMapper;
    private final UserRequestDtoMapper userRequestDtoMapper;
    private final UserService userService;

    @GetMapping("/user-edit/{userId}")
    public String editUserForm(@PathVariable(required = true) UUID userId, Model model) {
        //User userEntity = userResponseDtoMapper.fromDto(userService.findUserById(userId));
        //UserRequestFormDto user = userRequestDtoMapper.toDto(userEntity);
        //FIXME: !!!!!!!!!!!!!!!!!!!!!!!!

//        UserRequestDto user = userRequestDtoMapper.toDto(userService.findUserById(userId));

//        model.addAttribute("user", user);

        return "user/edit_user";
    }

    @PostMapping("/user-edit/{userId}")
    public String editUser(@PathVariable(required = true) UUID userId,
                            @Valid @ModelAttribute("user") UserRequestDto updatedUser,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "user/edit_user";

        updatedUser.setId(userId);
        userService.updateUser(updatedUser);

        model.addAttribute("messageBox",
                new MessageBox("user.edit.success", "user.edit.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
