package com.dmit.controller.car;

import com.dmit.dto.car.CarDto;
import com.dmit.service.CarImageService;
import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
public class ShowCarController {
    @Autowired
    CarService carService;
    @Autowired
    CarImageService carImageService;

    @GetMapping("/show-car/{carId}")
    String showCar(@PathVariable(required = true) UUID carId, Model model) {
        CarDto carDto = carService.getCar(carId);
        List<UUID> images = carImageService.getImageIdsByCarId(carId);

        model.addAttribute("car", carDto);
        model.addAttribute("images", images);

        return "car/show_car";
    }

    @ResponseBody
    @GetMapping(value = "/car-image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable UUID imageId) {
        return carImageService.getImage(imageId);
    }
}
