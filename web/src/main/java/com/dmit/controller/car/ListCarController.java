package com.dmit.controller.car;

import com.dmit.dto.CarDto;
import com.dmit.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ListCarController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CarService carService;

    @Transactional
    @GetMapping("car-list")
    public String carList(Model model) {
        List<CarDto> cars = carService.getAllCars().stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());

        model.addAttribute("cars", cars);

        return "list/car_list";
    }
}
