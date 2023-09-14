package com.dmit.dao;

import com.dmit.entity.car.CarModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelDao extends JpaRepository<CarModel, Long> {
    long countByCarBrand_Id(long brandId);

    @Query("SELECT m FROM CarModel m WHERE m.carBrand.id = ?1")
    Page<CarModel> findAllByBrand(long brandId, Pageable pageable);
}
