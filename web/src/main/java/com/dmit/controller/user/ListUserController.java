package com.dmit.controller.user;

import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_ADMIN")
public class ListUserController {
    @Autowired
    UserService userService;

    @GetMapping("user-list")
    public String userList(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("page", page);
        model.addAttribute("pages", (userService.countUsers() - 1) / 10 + 1);
        model.addAttribute("users", userService.getAllUsersPageable(page - 1, 10));

        return "user/list_user";
    }
}
