package com.dmit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarBrandDto {
    private Long id;
    @Size(min = 2, max = 14)
    private String brandName;
}
