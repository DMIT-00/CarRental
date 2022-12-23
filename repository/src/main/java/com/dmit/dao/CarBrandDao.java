package com.dmit.dao;

import com.dmit.entity.car.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandDao extends JpaRepository<CarBrand, Long> {
}
