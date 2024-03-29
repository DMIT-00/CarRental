package com.dmit.rest.order;

import com.dmit.dto.order.OrderDto;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dmit.config.RestConfig.DEFAULT_PAGE_SIZE;

@RestController
@Secured("ROLE_MANAGER")
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE)
                                                    int size) {
        List<OrderDto> orders = orderService.findAllOrdersPageable(page, size);
        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") UUID id) {
        OrderDto order = orderService.findOrderById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_MANAGER")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderRequestDto order) {
        OrderDto addedOrder = orderService.addOrder(order);

        return new ResponseEntity<>(addedOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") UUID id,
                                                @RequestBody OrderDto updatedOrder) {
        updatedOrder.setId(id);

        OrderDto resultOrder = orderService.updateOrder(updatedOrder);

        return new ResponseEntity<>(resultOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteOrder(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
