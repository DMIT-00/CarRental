package com.dmit.service;

import com.dmit.dao.CarImageDao;
import com.dmit.entity.car.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarImageService {
    @Autowired
    CarImageDao carImageDao;

    public Image getImage(UUID imageId) {
        return carImageDao.findById(imageId);
    }
}
