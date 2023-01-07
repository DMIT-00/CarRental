package com.dmit.controller.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.service.BrandService;
import com.dmit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_MANAGER")
public class AddModelController {
    @Autowired
    BrandService brandService;
    @Autowired
    ModelService modelService;

    @GetMapping("add-model")
    public String addModelForm(Model model) {
        model.addAttribute("model", new CarModelDto());
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

        return "car/add_model";
    }

    @PostMapping("add-model")
    public String addModel(@Valid @ModelAttribute("model") CarModelDto carModelDto, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAllBrands().stream()
                    .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

            return "car/add_model";
        }

        carModelDto.setId(null); // TODO: service level?

        modelService.addNewModel(carModelDto);

        return "redirect:/model-list";
    }
}
