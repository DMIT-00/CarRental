package com.dmit.controller.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.service.BrandService;
import com.dmit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ListModelController {
    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;

    @GetMapping("model-list")
    public String modelListForm(Model model) {
        Map<Long, String> brands = brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName));

        CarBrandDto currentBrand = new CarBrandDto();
        List<CarModelDto> modelsDto;

        if (brands.size() > 0) {
            Map.Entry<Long, String> firstBrand = brands.entrySet().iterator().next();

            currentBrand.setId(firstBrand.getKey());
            currentBrand.setBrandName(firstBrand.getValue());

            modelsDto = modelService.getAllBrandModels(currentBrand.getId());
        } else {
            modelsDto = new ArrayList<>();
        }

        model.addAttribute("brand", currentBrand);
        model.addAttribute("brands", brands);
        model.addAttribute("models", modelsDto);
        return "car/list_model";
    }

    @PostMapping("model-list")
    public String modelList(@ModelAttribute("brand") CarBrandDto currentBrand,
                            BindingResult bindingResult, Model model) {
//        if (currentBrand == null || bindingResult.hasErrors())
//            return "list/model_list";

        Map<Long, String> brands = brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName));

        List<CarModelDto> modelsDto;

        if (brands.size() > 0) {
            modelsDto = modelService.getAllBrandModels(currentBrand.getId());
        } else {
            modelsDto = new ArrayList<>();
        }

        model.addAttribute("brand", currentBrand);
        model.addAttribute("brands", brands);
        model.addAttribute("models", modelsDto);

        return "car/list_model";
    }
}
