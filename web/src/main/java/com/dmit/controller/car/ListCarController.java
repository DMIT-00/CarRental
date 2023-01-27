package com.dmit.controller.car;

import com.dmit.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListCarController {
    private final CarService carService;

    @GetMapping("car-list")
    public String carList(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("page", page);
        model.addAttribute("pages", (carService.countAllCars() - 1) / 10 + 1);
        model.addAttribute("cars", carService.findAllCarsPageable(page - 1, 10));

        return "car/list_car";
    }
}
