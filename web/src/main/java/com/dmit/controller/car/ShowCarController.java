package com.dmit.controller.car;

import com.dmit.dto.MessageBox;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.service.CarImageService;
import com.dmit.service.CarService;
import com.dmit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class ShowCarController {
    @Autowired
    CarService carService;
    @Autowired
    CarImageService carImageService;
    @Autowired
    OrderService orderService;

    @GetMapping("/show-car/{carId}")
    String showCar(@PathVariable(required = true) UUID carId, Model model) {
        CarDto carDto = carService.getCar(carId);
        List<UUID> images = carImageService.getImageIdsByCarId(carId);

        OrderRequestDto orderRequestDto = new OrderRequestDto(null, null, carId);

        model.addAttribute("car", carDto);
        model.addAttribute("images", images);
        model.addAttribute("order", orderRequestDto);

        return "car/show_car";
    }

    @PostMapping("/show-car/{carId}")
    String orderForm(@PathVariable(required = true) UUID carId, Model model,
                     @Valid @ModelAttribute("order") OrderRequestDto orderRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            CarDto carDto = carService.getCar(carId);
            List<UUID> images = carImageService.getImageIdsByCarId(carId);

            model.addAttribute("car", carDto);
            model.addAttribute("images", images);

            return "car/show_car";
        }

        orderService.createNewOrder(orderRequestDto);

        model.addAttribute("messageBox",
                new MessageBox("order.success", "order.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }

    @ResponseBody
    @GetMapping(value = "/car-image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable UUID imageId) {
        return carImageService.getImage(imageId);
    }
}
