package com.dmit.controller.order;

import com.dmit.dto.MessageBox;
import com.dmit.dto.order.OrderDto;
import com.dmit.service.OrderService;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditOrderController {
    private final OrderService orderService;

    @Secured("ROLE_MANAGER")
    @GetMapping("/order-edit/{orderId}")
    public String editOrder(@PathVariable(required = true) UUID orderId, Model model) {
        OrderDto orderDto = orderService.findOrderById(orderId);

        model.addAttribute("order", orderDto);

        return "order/edit_order";
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("/order-edit/{orderId}")
    public String editOrder(@PathVariable(required = true) UUID orderId,
                            @Valid @ModelAttribute("order") OrderDto updatedOrder,
                            BindingResult bindingResult, Model model) {
        OrderDto order = orderService.findOrderById(orderId);

        boolean isUserBusy = orderService.isUserBusyForOrderExceptOrder(
                order.getId(),
                order.getUser().getId(),
                updatedOrder.getStartDate(),
                updatedOrder.getEndDate()
        );
        if (isUserBusy)
            model.addAttribute("userBusyError", true);

        boolean isCarBusy = orderService.isCarBusyForOrderExceptOrder(
                order.getId(),
                order.getCar().getId(),
                updatedOrder.getStartDate(),
                updatedOrder.getEndDate()
        );
        if (isCarBusy)
            model.addAttribute("carBusyError", true);

        if (bindingResult.hasErrors() || isUserBusy || isCarBusy) {
            model.addAttribute("order", order);

            return "order/edit_order";
        }

        updatedOrder.setId(orderId);
        orderService.updateOrder(updatedOrder);

        model.addAttribute("messageBox",
                new MessageBox("order.edit_success", "order.edit_success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
