package com.dmit.controller.api;

import com.dmit.dto.CarDto;
import com.dmit.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListCarRestController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CarService carService;

    @GetMapping("/api/v1/get_all_cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars().stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }
}
