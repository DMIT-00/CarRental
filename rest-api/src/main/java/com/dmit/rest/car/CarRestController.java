package com.dmit.rest.car;

import com.dmit.dto.car.CarDto;
import com.dmit.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarRestController {
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        List<CarDto> cars = carService.getAllCarsPageable(page, size);
        if (cars.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
