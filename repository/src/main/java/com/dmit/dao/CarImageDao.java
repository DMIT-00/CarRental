package com.dmit.dao;

import com.dmit.entity.car.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarImageDao extends JpaRepository<Image, UUID> {
    @Query(value = "SELECT image_id FROM t_image WHERE car_id = :carId", nativeQuery = true)
    List<UUID> getAllImagesIdByCarId(@Param("carId") String carId);
}
