package com.dmit.dao;

import com.dmit.entity.car.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarImageDao extends JpaRepository<Image, UUID> {
}
