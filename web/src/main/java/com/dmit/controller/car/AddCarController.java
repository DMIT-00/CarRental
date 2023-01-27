package com.dmit.controller.car;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddCarController {
    private final CarService carService;
    private final ModelService modelService;
    private final BrandService brandService;

    @GetMapping("car-add")
    public String addCarForm(Model model) {
        CarDto carDto = new CarDto();
        Map<Long, String> modelsMap;

        Map<Long, String> brandsMap = brandService.findAllBrandsPageable(0, 100).stream()
                .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName));

        // Set the first brand as current, if exists
        if (brandsMap.size() > 0) {

            Map.Entry<Long, String> firstBrand = brandsMap.entrySet().iterator().next();
            CarBrandDto carBrandDto = new CarBrandDto(firstBrand.getKey(), firstBrand.getValue());

            modelsMap = modelService.findAllModelsPageableByBrand(carBrandDto.getId(), 0, 100).stream()
                    .collect(Collectors.toMap(CarModelDto::getId, CarModelDto::getModelName));

            // Set the first model as current, if exists
            if (modelsMap.size() > 0) {
                Map.Entry<Long, String> firstModel = brandsMap.entrySet().iterator().next();
                carDto.setCarModel(new CarModelDto(firstModel.getKey(), firstModel.getValue(), carBrandDto));
            } else {
                carDto.setCarModel(new CarModelDto(null, null, carBrandDto));
            }
        } else {
            modelsMap = new HashMap<>();
        }

        model.addAttribute("car", carDto);
        model.addAttribute("models", modelsMap);
        model.addAttribute("brands", brandsMap);

        return "car/add_car";
    }

    @PostMapping(value = "car-add")
    public String addCar(@Valid @ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                         @RequestParam(value = "submit-car", required = false) String action, Model model,
                         RedirectAttributes redirectAttrs) {
        // Binding has errors, or page refreshed on select change,
        // just update the page with errors and CarModel and CarBrand from database
        if (bindingResult.hasErrors() || action == null) {
            Map<Long, String> models = modelService.findAllModelsPageableByBrand(carDto.getCarModel()
                            .getCarBrand().getId(), 0, 100).stream()
                            .collect(Collectors.toMap(CarModelDto::getId, CarModelDto::getModelName));

            model.addAttribute("models", models);
            model.addAttribute("brands", brandService.findAllBrandsPageable(0, 100).stream()
                    .collect(Collectors.toMap(CarBrandDto::getId, CarBrandDto::getBrandName)));

            return "car/add_car";
        }

        CarDto addedCar = carService.addCar(carDto);

        redirectAttrs.addFlashAttribute("carId", addedCar.getId());

        return "redirect:/images-add";
    }
}
