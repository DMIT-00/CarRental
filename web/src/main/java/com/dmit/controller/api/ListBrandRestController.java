package com.dmit.controller.api;

import com.dmit.dto.CarBrandDto;
import com.dmit.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListBrandRestController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BrandService brandService;

    @GetMapping("/api/v1/get_all_brands")
    public List<CarBrandDto> getAllBrands() {
        return brandService.getAllBrands().stream()
                .map(brand -> modelMapper.map(brand, CarBrandDto.class))
                .collect(Collectors.toList());
    }
}
