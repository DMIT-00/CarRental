package com.dmit.controller.car;

import com.dmit.dto.CarModelDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.service.BrandService;
import com.dmit.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class AddModelController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BrandService brandService;
    @Autowired
    ModelService modelService;

    @GetMapping("add-model")
    public String addModelForm(Model model) {
        model.addAttribute("model", new CarModelDto());
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

        return "add/add_model";
    }

    @PostMapping("add-model")
    public String addModel(@Valid @ModelAttribute("model") CarModelDto carModelDto, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAllBrands().stream()
                    .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

            return "add/add_model";
        }

        carModelDto.setId(null); // TODO: service level?

        CarModel carModel = modelMapper.map(carModelDto, CarModel.class);

        modelService.addNewModel(carModel);

        return "redirect:/model-list";
    }
}
