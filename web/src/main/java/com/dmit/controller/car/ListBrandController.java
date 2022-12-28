package com.dmit.controller.car;

import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
public class ListBrandController {
    @Autowired
    BrandService brandService;

    @Transactional
    @GetMapping("brand-list")
    public ModelAndView brandList() {
        return new ModelAndView("car/list_brand", Map.of("brands", brandService.getAllBrands()));
    }
}
