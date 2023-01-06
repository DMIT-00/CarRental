package com.dmit.controller.car;

import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListCarController {
    @Autowired
    CarService carService;

    @GetMapping("car-list")
    public String carList(Model model) {
        model.addAttribute("cars", carService.getAllCars());

        return "car/list_car";
    }
}
