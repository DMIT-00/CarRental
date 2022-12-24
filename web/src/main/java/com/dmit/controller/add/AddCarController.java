package com.dmit.controller.add;

import com.dmit.dto.CarBrandDto;
import com.dmit.dto.CarDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

            carDto.setCarBrand(new CarBrandDto(firstBrand.getKey(), firstBrand.getValue()));

            modelsMap = modelService.getAllBrandModels(carDto.getCarBrand().getId()).stream()
                    .collect(Collectors.toMap(CarModel::getId, CarModel::getModelName));
        } else {
            modelsMap = new HashMap<>();
        }

        model.addAttribute("car", carDto);
        model.addAttribute("models", modelsMap);
        model.addAttribute("brands", brandsMap);

        return "add/add_car";
    }

    //  This method updates form on select input changes
    @PostMapping(value = "add-car")
    public String addCarRefresh(@ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                         Model model) {
        Map<Long, String> models = modelService.getAllBrandModels(carDto.getCarBrand().getId()).stream()
                .collect(Collectors.toMap(CarModel::getId, CarModel::getModelName));

        model.addAttribute("car", carDto);
        model.addAttribute("models", models);
        model.addAttribute("brands", brandService.getAllBrands().stream()
                .collect(Collectors.toMap(CarBrand::getId, CarBrand::getBrandName)));

        return "add/add_car";
    }

    @PostMapping(value = "add-car", params = "submit-car")
    public String addCar(@ModelAttribute("car") CarDto carDto, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttrs) {
        carDto.setId(null); // TODO: service level?

        // Mapper won't set car.carBrand without it
        carDto.getCarModel().setCarBrand(carDto.getCarBrand());
        Car car = modelMapper.map(carDto, Car.class);

        carService.addNewCar(car);

        redirectAttrs.addFlashAttribute("carId", car.getId());

        return "redirect:/add-images";
    }
}
