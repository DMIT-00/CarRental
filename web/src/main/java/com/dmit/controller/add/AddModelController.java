package com.dmit.controller.add;

import com.dmit.dto.ModelDto;
import com.dmit.dto.ModelDtoMapper;
import com.dmit.entity.car.CarBrand;
import com.dmit.service.BrandService;
import com.dmit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class AddModelController {
    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;

    @GetMapping("add-model")
    public String addModelForm(Model model) {
        model.addAttribute("model", new ModelDto());
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

        return "add/add_model";
    }

    @PostMapping("add-model")
    public String addModel(@ModelAttribute("model") ModelDto modelDto, BindingResult bindingResult) {
        //        if (bindingResult.hasErrors())
//            return "redirect:/add-brand";

        modelDto.setId(null); // TODO: service level?

        modelService.addNewModel(ModelDtoMapper.fromDto(modelDto));

        return "redirect:/model-list";
    }
}
