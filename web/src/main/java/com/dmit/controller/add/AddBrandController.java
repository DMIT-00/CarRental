package com.dmit.controller.add;

import com.dmit.dto.BrandDto;
import com.dmit.dto.BrandDtoMapper;
import com.dmit.service.BrandService;
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
    BrandService brandService;

    @GetMapping("add-brand")
    public String addBrandForm(Model model) {
        model.addAttribute("brand", new BrandDto());

        return "add/add_brand";
    }

    @PostMapping("add-brand")
    public String addBrand(@ModelAttribute("brand") BrandDto brandDto, BindingResult bindingResult,
                           Model model) {
//        if (bindingResult.hasErrors())
//            return "redirect:/add-brand";

        brandDto.setId(null); // TODO: service level?

        brandService.addNewBrand(BrandDtoMapper.fromDto(brandDto));

        return "redirect:/brand-list";
    }
}
