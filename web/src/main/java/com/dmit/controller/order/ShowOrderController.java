package com.dmit.controller.order;

import com.dmit.dto.order.OrderDto;
import com.dmit.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShowOrderController {
    private final OrderService orderService;

    @Secured("ROLE_MANAGER")
    @GetMapping("/order-show/{orderId}")
    public String showOrder(@PathVariable(required = true) UUID orderId, Model model) {
        OrderDto orderDto = orderService.findOrderById(orderId);

        model.addAttribute("order", orderDto);

        return "order/show_order";
    }
}
