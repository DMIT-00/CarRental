package com.dmit.controller.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.service.BrandService;
import com.dmit.service.CarService;
import com.dmit.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AddCarController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CarService carService;
    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;

    @GetMapping("add-car")
    public String addCarForm(Model model) {
        CarDto carDto = new CarDto();
        Map<Long, String> modelsMap;

        Map<Long, String> brandsMap = brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName));

        // Set the first brand as current, if exists
        if (brandsMap.size() > 0) {

            Map.Entry<Long, String> firstBrand = brandsMap.entrySet().iterator().next();
            CarBrandDto carBrandDto = new CarBrandDto(firstBrand.getKey(), firstBrand.getValue());

            modelsMap = modelService.getAllBrandModels(carBrandDto.getId()).stream()
                    .collect(Collectors.toMap(CarModel::getId, CarModel::getModelName));

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

    @PostMapping(value = "add-car")
    public String addCar(@Valid @ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                         @RequestParam(value = "submit-car", required = false) String action, Model model,
                         RedirectAttributes redirectAttrs) {
        // Binding has errors, or page refreshed on select change,
        // just update the page with errors and CarModel and CarBrand from database
        if (bindingResult.hasErrors() || action == null) {
            Map<Long, String> models = modelService.getAllBrandModels(carDto.getCarModel()
                            .getCarBrand().getId()).stream()
                            .collect(Collectors.toMap(CarModel::getId, CarModel::getModelName));

            // Don't think that is needed
            //model.addAttribute("car", carDto);
            model.addAttribute("models", models);
            model.addAttribute("brands", brandService.getAllBrands().stream()
                    .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

            return "car/add_car";
        }

        carDto.setId(null); // TODO: service level?

        Car car = modelMapper.map(carDto, Car.class);

        carService.addNewCar(car);

        redirectAttrs.addFlashAttribute("carId", car.getId());

        return "redirect:/add-images";
    }
}
