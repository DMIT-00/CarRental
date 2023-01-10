package com.dmit.controller.order;

import com.dmit.dto.order.OrderDto;
import com.dmit.entity.order.OrderStatus;
import com.dmit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListOrderController {
    @Autowired
    OrderService orderService;

    @Secured("ROLE_MANAGER")
    @GetMapping("order-list")
    public String orderList(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "filter", required = false, defaultValue = "") String filter
    ) {
        long numberOfPages;
        List<OrderDto> orders;
        OrderStatus orderStatus = null;

        switch (filter) {
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

        if (orderStatus != null) {
            numberOfPages = (orderService.countOrdersByStatus(orderStatus) - 1) / 10 + 1;
            orders = orderService.getAllOrdersPageableByStatus(orderStatus, page - 1, 10);
        } else {
            numberOfPages = (orderService.countOrders() - 1) / 10 + 1;
            orders = orderService.getAllOrdersPageable(page - 1, 10);
        }

        model.addAttribute("page", page);
        model.addAttribute("orders", orders);
        model.addAttribute("pages", numberOfPages);

        return "order/list_order";
    }
}