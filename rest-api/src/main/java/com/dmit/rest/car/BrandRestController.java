package com.dmit.rest.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.exception.NotFoundException;
import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CarBrandDto> getBrand(@PathVariable("id") Long id) {
        CarBrandDto brand;

        try {
            brand = brandService.findBrandById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarBrandDto> addBrand(CarBrandDto brand) {
        CarBrandDto addedBrand = brandService.addNewBrand(brand);
        return new ResponseEntity<>(addedBrand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandDto> updateBrand(@PathVariable("id") Long id, @RequestBody CarBrandDto updatedBrand) {
        CarBrandDto resultBrand;

        updatedBrand.setId(id);

        try {
            resultBrand = brandService.updateBrand(updatedBrand);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultBrand, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id) {
        try {
            brandService.deleteBrand(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
