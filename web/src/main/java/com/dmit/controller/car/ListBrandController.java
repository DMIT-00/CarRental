package com.dmit.controller.car;

import com.dmit.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListBrandController {
    private final BrandService brandService;

    @GetMapping("brand-list")
    public ModelAndView brandList() {
        return new ModelAndView("car/list_brand",
                Map.of("brands", brandService.findAllBrandsPageable(0, 100)));
    }
}
