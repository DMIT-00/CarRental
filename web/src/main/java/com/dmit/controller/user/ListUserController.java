package com.dmit.controller.user;

import com.dmit.dto.user.UserResponseDto;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class ListUserController {
    @Autowired
    UserService userService;

    @GetMapping("user-list")
    public String userList(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "filter", required = false, defaultValue = "") String filter
    ) {
        model.addAttribute("page", page);

        long numberOfPages;
        List<UserResponseDto> users;

        switch (filter) {
            case "active":
                numberOfPages = (userService.countNotBlockedUsers() - 1) / 10 + 1;
                users = userService.getNotBlockedUsersPageable(page - 1, 10);
                break;
            case "blocked":
                numberOfPages = (userService.countBlockedUsers() - 1) / 10 + 1;
                users = userService.getBlockedUsersPageable(page - 1, 10);
                break;
            case "active_order":
                numberOfPages = (userService.countActiveOrderUsers() - 1) / 10 + 1;
                users = userService.getActiveOrderUsersPageable(page - 1, 10);
                break;
            case "inactive_order":
                numberOfPages = (userService.countInactiveOrderUsers() - 1) / 10 + 1;
                users = userService.getInactiveOrderUsersPageable(page - 1, 10);
                break;
            default:
                numberOfPages = (userService.countUsers() - 1) / 10 + 1;
                users = userService.getAllUsersPageable(page - 1, 10);
                break;
        }

        model.addAttribute("users", users);
        model.addAttribute("pages", numberOfPages);

        return "user/list_user";
    }
}
