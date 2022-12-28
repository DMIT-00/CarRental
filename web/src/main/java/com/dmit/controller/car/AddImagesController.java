package com.dmit.controller.car;

import com.dmit.entity.car.Image;
import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

// TODO: is SessionAttributes ok?
@Controller
@SessionAttributes("carId")
public class AddImagesController {
    @Autowired
    CarService carService;


    // FIXME:: call security! Only car owner can do that!
    @GetMapping(value = "add-images")
    public String addCarImagesForm(Model model) {
        return "car/add_images";
    }

    // FIXME:: call security! Only car owner can do that!
    @PostMapping(value = "add-images")
    public String addCarImages(@ModelAttribute("images") MultipartFile[] files, Model model) {

        List<Image> images = new ArrayList<>();

        Arrays.stream(files)
                .filter(Predicate.not(MultipartFile::isEmpty))
                .forEach(f -> {
                    try {
                        images.add(new Image(null, f.getBytes()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        carService.updateCarImages((UUID) model.getAttribute("carId"), images);

        return "redirect:/car-list";
    }
}
