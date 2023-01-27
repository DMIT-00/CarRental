package com.dmit.service;

import com.dmit.dao.CarImageDao;
import com.dmit.entity.car.Image;
import com.dmit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarImageServiceImpl implements CarImageService {
    private final CarImageDao imageDao;

    @Override
    @Transactional
    public byte[] getImage(UUID imageId) {
        Image image = imageDao.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not found! Id: " + imageId));
        return image.getImage();
    }

    @Override
    @Transactional
    public List<UUID> getImageIdsByCarId(UUID carId) {
        return imageDao.getAllImagesIdByCarId(carId.toString());
    }
}
