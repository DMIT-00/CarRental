package com.dmit.controller;

import com.dmit.dto.CarDto;
import com.dmit.dto.CarDtoMapper;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
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

    // TODO: security
    @GetMapping("/show-car/{carId}")
    String showCar(@PathVariable(required = true) UUID carId, Model model) {
        Car car = carService.getCar(carId);
        List<Image> images = car.getImages();

        CarDto carDto = CarDtoMapper.toDto(carService.getCar(carId));

        model.addAttribute("carDto", carDto);
        model.addAttribute("images", images);

        return "show_car";
    }

    @ResponseBody
    @GetMapping(value = "/car-image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable UUID imageId) {
        Image image = carImageService.getImage(imageId);
        return image.getImage();
    }
}
