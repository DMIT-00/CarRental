package com.dmit.controller.list;

import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
public class CarListController {
    @Autowired
    CarService carService;

    @Transactional
    @GetMapping("car-list")
    public ModelAndView carList() {
        return new ModelAndView("list/car_list", Map.of("cars", carService.getAllCars()));
    }
}
