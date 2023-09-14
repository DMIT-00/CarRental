package com.dmit.dao;

import com.dmit.config.DataConfig;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@TestPropertySource(value = {
        "classpath:/database-test.properties",
        "classpath:/hibernate-test.properties"
})
public class CarModelDaoTest {
    @Autowired
    private CarModelDao carModelDao;
    @Autowired
    private CarBrandDao carBrandDao;

    @Before
    public void setUp() {
        // FIXME: should only run once before all tests
        CarBrand brand1 = new CarBrand(1L, "BMW", new ArrayList<>());
        CarModel model1 = new CarModel(1L, "X1", null);
        CarModel model2 = new CarModel(2L, "X3", null);
        CarModel model3 = new CarModel(3L, "X5", null);
        brand1.addModel(model1);
        brand1.addModel(model2);
        brand1.addModel(model3);
        carBrandDao.save(brand1);
        carModelDao.save(model1);
        carModelDao.save(model2);
        carModelDao.save(model3);

        CarBrand brand2 = new CarBrand(2L, "KIA", new ArrayList<>());
        CarModel model4 = new CarModel(4L, "CR", null);
        brand2.addModel(model4);
        carBrandDao.save(brand2);
        carModelDao.save(model4);
    }

    @Test
    public void countByCarBrand_Id() {
        // Given
        // Database initialized with entities in setUp() method of this class

        // When
        long numberOfModels = carModelDao.countByCarBrand_Id(1);

        // Then
        assertEquals(3, numberOfModels);
    }

    @Test
    public void findAllByBrand() {
        // Given
        // Database initialized with entities in setUp() method of this class

        // When
        Page<CarModel> models = carModelDao.findAllByBrand(1, PageRequest.of(0, 20));

        // Then
        assertEquals(3, models.getContent().size());
    }
}