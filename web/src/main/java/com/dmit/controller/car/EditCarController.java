package com.dmit.controller.car;

import com.dmit.dto.MessageBox;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.service.BrandService;
import com.dmit.service.CarService;
import com.dmit.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditCarController {
    private final CarService carService;
    private final BrandService brandService;
    private final ModelService modelService;

    @GetMapping("/car-edit/{carId}")
    public String editCarForm(@PathVariable(required = true) UUID carId, Model model) {
        CarDto car = carService.findCarById(carId);

        Map<Long, String> brandsMap = brandService.findAllBrandsPageable(0, 100).stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName));

        Map<Long, String> modelsMap = modelService.findAllModelsPageableByBrand(
                car.getCarModel().getCarBrand().getId(), 0, 100).stream()
                .collect(Collectors.toMap(CarModelDto::getId, CarModelDto::getModelName));

        model.addAttribute("car", car);
        model.addAttribute("models", modelsMap);
        model.addAttribute("brands", brandsMap);

        return "car/edit_car";
    }

    @PostMapping("/car-edit/{carId}")
    public String editCar(@PathVariable(required = true) UUID carId,
                            @RequestParam(value = "submit-car", required = false) String action,
                            @Valid @ModelAttribute("car") CarDto updatedCar,
                            BindingResult bindingResult, Model model) {
        // Need to set it each time, because the value is lost during each refresh
        updatedCar.setId(carId);

        // Binding has errors, or page refreshed on select change,
        // just update the page with errors and CarModel and CarBrand from database
        if (bindingResult.hasErrors() || action == null) {
            Map<Long, String> brandsMap = brandService.findAllBrandsPageable(0, 100).stream()
                    .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName));

            Map<Long, String> modelsMap = modelService.findAllModelsPageableByBrand(
                            updatedCar.getCarModel().getCarBrand().getId(), 0, 100).stream()
                    .collect(Collectors.toMap(CarModelDto::getId, CarModelDto::getModelName));

            model.addAttribute("models", modelsMap);
            model.addAttribute("brands", brandsMap);
            return "car/edit_car";
        }

        carService.updateCar(updatedCar);

        model.addAttribute("messageBox",
                new MessageBox("car.edit.success", "car.edit.success_full", MessageBox.MessageBoxType.SUCCESS));
        return "message_box";
    }
}
