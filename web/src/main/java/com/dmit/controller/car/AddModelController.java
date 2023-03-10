package com.dmit.controller.car;

import com.dmit.dto.MessageBox;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.service.BrandService;
import com.dmit.service.ModelService;
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
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddModelController {
    private final BrandService brandService;
    private final ModelService modelService;

    @GetMapping("model-add")
    public String addModelForm(Model model) {
        model.addAttribute("model", new CarModelDto());
        model.addAttribute("brands", brandService.findAllBrandsPageable(0, 100).stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

        return "car/add_model";
    }

    @PostMapping("model-add")
    public String addModel(@Valid @ModelAttribute("model") CarModelDto carModelDto, BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.findAllBrandsPageable(0, 100).stream()
                    .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

            return "car/add_model";
        }

        modelService.addModel(carModelDto);

        model.addAttribute("messageBox",
                new MessageBox("add_model.success", "add_model.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
