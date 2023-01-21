package com.dmit.rest.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.exception.NotFoundException;
import com.dmit.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dmit.config.RestConfig.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController {
    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<List<CarBrandDto>> getBrands(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE)
                                                       int size) {
        List<CarBrandDto> brands = brandService.findAllBrandsPageable(page, size);
        if (brands.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarBrandDto> getBrand(@PathVariable("id") long id) {
        CarBrandDto brand;

        try {
            brand = brandService.findBrandById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_MANAGER")
    public ResponseEntity<CarBrandDto> addBrand(@RequestBody CarBrandDto brand) {
        CarBrandDto addedBrand = brandService.addBrand(brand);
        return new ResponseEntity<>(addedBrand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<CarBrandDto> updateBrand(@PathVariable("id") long id,
                                                   @RequestBody CarBrandDto updatedBrand) {
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
    @Secured("ROLE_MANAGER")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") long id) {
        try {
            brandService.deleteBrand(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
