package com.dmit.rest.car;

import com.dmit.dto.car.CarDto;
import com.dmit.exception.NotFoundException;
import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dmit.config.RestConfig.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/v1/cars")
public class CarRestController {
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE)
                                                int size) {
        List<CarDto> cars = carService.findAllCarsPageable(page, size);
        if (cars.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id") UUID id) {
        CarDto car;

        try {
            car = carService.findCarById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_MANAGER")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto car) {
        CarDto addedCar = carService.addCar(car);
        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") UUID id,
                                            @RequestBody CarDto updatedCar) {
        CarDto resultCar;

        updatedCar.setId(id);

        try {
            resultCar = carService.updateCar(updatedCar);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<?> deleteCar(@PathVariable("id") UUID id) {
        try {
            carService.deleteCar(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
