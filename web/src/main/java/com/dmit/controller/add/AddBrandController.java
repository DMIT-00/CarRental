package com.dmit.controller.add;

import com.dmit.dto.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddBrandController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BrandService brandService;

    @GetMapping("add-brand")
    public String addBrandForm(Model model) {
        model.addAttribute("brand", new CarBrandDto());

        return "add/add_brand";
    }

    @PostMapping("add-brand")
    public String addBrand(@ModelAttribute("brand") CarBrandDto carBrandDto, BindingResult bindingResult,
                           Model model) {
//        if (bindingResult.hasErrors())
//            return "redirect:/add-brand";

        carBrandDto.setId(null); // TODO: service level?

        brandService.addNewBrand(modelMapper.map(carBrandDto, CarBrand.class));

        return "redirect:/brand-list";
    }
}
