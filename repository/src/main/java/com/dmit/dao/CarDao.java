package com.dmit.dao;

import com.dmit.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarDao extends JpaRepository<Car, UUID> {
}
