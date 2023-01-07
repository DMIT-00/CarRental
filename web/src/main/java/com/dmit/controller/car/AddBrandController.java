package com.dmit.controller.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Secured("ROLE_MANAGER")
public class AddBrandController {
    @Autowired
    BrandService brandService;

    @GetMapping("add-brand")
    public String addBrandForm(Model model) {
        model.addAttribute("brand", new CarBrandDto());

        return "car/add_brand";
    }

    @PostMapping("add-brand")
    public String addBrand(@Valid @ModelAttribute("brand") CarBrandDto carBrandDto, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors())
            return "car/add_brand";

        carBrandDto.setId(null); // TODO: service level?

        brandService.addNewBrand(carBrandDto);

        return "redirect:/brand-list";
    }
}
