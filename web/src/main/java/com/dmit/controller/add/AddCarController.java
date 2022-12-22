package com.dmit.controller.add;

import com.dmit.dto.*;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.service.BrandService;
import com.dmit.service.CarService;
import com.dmit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AddCarController {

    @Autowired
    CarService carService;
    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;

    @GetMapping("add-car")
    public String addCarForm(Model model) {
        model.addAttribute("car", new CarDto());
        model.addAttribute("models", new HashMap<>()); // FIXME
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

        return "add/add_car";
    }

    @PostMapping(value = "add-car")
    public String addCarRefresh(@ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                         Model model) {
        //carDto.setId(null); // TODO: service level?
        //carService.addNewCar(CarDtoMapper.fromDto(carDto));

        Map<Long, String> models = modelService.getAllBrandModels(carDto.getBrandId()).stream()
                .collect(Collectors.toMap(CarModel::getId, CarModel::getModelName));

        model.addAttribute("car", carDto);
        model.addAttribute("models", models);
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

        return "add/add_car";
    }

    @PostMapping(value = "add-car", params = "submit-car")
    public String addCar(@ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                         Model model) {
        carDto.setId(null); // TODO: service level?
        carService.addNewCar(CarDtoMapper.fromDto(carDto));


        return "redirect:/car-list"; // TODO: redirect somewhere else on success
    }
}
