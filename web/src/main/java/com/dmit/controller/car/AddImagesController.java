package com.dmit.controller.car;

import com.dmit.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

@Controller
@SessionAttributes("carId")
@Secured("ROLE_MANAGER")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddImagesController {
    private final CarService carService;

    @GetMapping(value = "images-add")
    public String addCarImagesForm(Model model) {
        return "car/add_images";
    }

    @PostMapping(value = "images-add")
    public String addCarImages(@ModelAttribute("images") MultipartFile[] files, Model model) {

        List<byte[]> images = new ArrayList<>();

        Arrays.stream(files)
                .filter(Predicate.not(MultipartFile::isEmpty))
                .forEach(f -> {
                    try {
                        images.add(f.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        carService.updateCarImages((UUID) model.getAttribute("carId"), images);

        return "redirect:/car-list";
    }
}
