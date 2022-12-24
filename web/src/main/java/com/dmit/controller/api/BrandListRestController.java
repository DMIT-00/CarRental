package com.dmit.controller.api;

import com.dmit.dto.BrandDto;
import com.dmit.dto.BrandDtoMapper;
import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BrandListRestController {
    @Autowired
    BrandService brandService;

    @GetMapping("/api/v1/get_all_brands")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands().stream().map(BrandDtoMapper::toDto).collect(Collectors.toList());
    }
}
