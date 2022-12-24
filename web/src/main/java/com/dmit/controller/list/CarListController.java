package com.dmit.controller.list;

import com.dmit.dto.CarDto;
import com.dmit.dto.CarDtoMapper;
import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarListController {
    @Autowired
    CarService carService;

    @Transactional
    @GetMapping("car-list")
    public String carList(Model model) {
        List<CarDto> cars = carService.getAllCars().stream()
                .map(CarDtoMapper::toDto)
                .collect(Collectors.toList());

        model.addAttribute("cars", cars);

        return "list/car_list";
    }
}
