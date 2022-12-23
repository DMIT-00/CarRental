package com.dmit.dao;

import com.dmit.entity.car.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelDao extends JpaRepository<CarModel, Long> {
    @Query("SELECT m FROM CarModel m WHERE m.carBrand.id = ?1")
    List<CarModel> findAllByBrand(long brandId);
}
