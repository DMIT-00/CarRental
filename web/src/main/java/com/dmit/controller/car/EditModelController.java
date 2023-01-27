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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditModelController {
    private final ModelService modelService;
    private final BrandService brandService;

    @GetMapping("/model-edit/{modelId}")
    public String editModelForm(@PathVariable(required = true) Long modelId, Model model) {
        model.addAttribute("model", modelService.findModelById(modelId));
        model.addAttribute("brands", brandService.findAllBrandsPageable(0, 100).stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

        return "car/edit_model";
    }

    @PostMapping("/model-edit/{modelId}")
    public String editModel(@PathVariable(required = true) Long modelId,
                            @Valid @ModelAttribute("model") CarModelDto updatedModel,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "car/edit_model";

        updatedModel.setId(modelId);
        modelService.updateModel(updatedModel);

        model.addAttribute("messageBox",
                new MessageBox("model.edit.success", "model.edit.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
