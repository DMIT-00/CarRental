package com.dmit.dto.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelDto {
    private Long id;
    @NotNull
    @Size(min = 2, max = 14)
    private String modelName;
    @NotNull
    private CarBrandDto carBrand;
}
