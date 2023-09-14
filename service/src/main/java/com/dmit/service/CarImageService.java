package com.dmit.service;

import java.util.List;
import java.util.UUID;

public interface CarImageService {
    byte[] getImage(UUID imageId);

    List<UUID> getImageIdsByCarId(UUID carId);
}
