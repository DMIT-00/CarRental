package com.dmit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelDto {
    private Long id;
    private String modelName;
    private CarBrandDto carBrand;
}
