package com.dmit.rest.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController {
    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<List<CarBrandDto>> getBrands() {
        // TODO: Pagination?
        List<CarBrandDto> brands = brandService.getAllBrands();
        if (brands.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
