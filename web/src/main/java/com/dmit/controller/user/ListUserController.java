package com.dmit.controller.user;

import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.order.OrderStatus;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Secured("ROLE_MANAGER")
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
        Boolean locked = null;
        OrderStatus orderStatus = null;

        switch (filter) {
            case "active":
                locked = false;
                break;
            case "blocked":
                locked = true;
                break;
            case "payment":
                orderStatus = OrderStatus.PAYMENT;
                break;
            case "paid":
                orderStatus = OrderStatus.PAID;
                break;
            case "car_in_use":
                orderStatus = OrderStatus.CAR_IN_USE;
                break;
            case "car_returned":
                orderStatus = OrderStatus.CAR_RETURNED;
                break;
            case "closed":
                orderStatus = OrderStatus.CLOSED;
                break;
        }

        if (locked != null) {
            numberOfPages = (userService.countUsersByLocked(locked) - 1) / 10 + 1;
            users = userService.findUsersByLocked(locked, page - 1, 10);
        } else if (orderStatus != null) {
            numberOfPages = (userService.countUsersByOrder(orderStatus) - 1) / 10 + 1;
            users = userService.findUsersByOrder(orderStatus, page - 1, 10);
        } else {
            numberOfPages = (userService.countUsers() - 1) / 10 + 1;
            users = userService.getAllUsersPageable(page - 1, 10);
        }

        model.addAttribute("users", users);
        model.addAttribute("pages", numberOfPages);

        return "user/list_user";
    }
}
