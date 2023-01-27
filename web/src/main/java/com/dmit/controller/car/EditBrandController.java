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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditBrandController {
    private final BrandService brandService;

    @GetMapping("/brand-edit/{brandId}")
    public String editBrandForm(@PathVariable(required = true) Long brandId, Model model) {
        CarBrandDto carBrand = brandService.findBrandById(brandId);

        model.addAttribute("brand", carBrand);

        return "car/edit_brand";
    }

    @PostMapping("/brand-edit/{brandId}")
    public String editBrand(@PathVariable(required = true) Long brandId,
                            @Valid @ModelAttribute("brand") CarBrandDto updatedBrand,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "car/edit_brand";

        updatedBrand.setId(brandId);
        brandService.updateBrand(updatedBrand);

        model.addAttribute("messageBox",
                new MessageBox("brand.edit.success", "brand.edit.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
