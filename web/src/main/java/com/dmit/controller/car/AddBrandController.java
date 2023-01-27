package com.dmit.controller.car;

import com.dmit.dto.MessageBox;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.service.BrandService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBrandController {
    private final BrandService brandService;

    @GetMapping("brand-add")
    public String addBrandForm(Model model) {
        model.addAttribute("brand", new CarBrandDto());

        return "car/add_brand";
    }

    @PostMapping("brand-add")
    public String addBrand(@Valid @ModelAttribute("brand") CarBrandDto carBrandDto, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors())
            return "car/add_brand";

        brandService.addBrand(carBrandDto);

        model.addAttribute("messageBox",
                new MessageBox("add_brand.success", "add_brand.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
