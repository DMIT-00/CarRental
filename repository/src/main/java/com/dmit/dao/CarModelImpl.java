package com.dmit.dao;

import com.dmit.entity.car.CarModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CarModelImpl implements CarModelDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(CarModel object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public CarModel read(CarModel object) {
        return findById(object.getId());
    }

    @Override
    public void update(CarModel object) {
        create(object);
    }

    @Override
    public void delete(CarModel object) {
        CarModel loadedObject = sessionFactory.getCurrentSession().load(CarModel.class, object.getId());
        sessionFactory.getCurrentSession().delete(loadedObject);
    }

    @Override
    public CarModel findById(Long id) {
        return sessionFactory.getCurrentSession().get(CarModel.class, id);
    }

    @Override
    public List<CarModel> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM CarModel", CarModel.class).list();
    }

    @Override
    public List<CarModel> findAllByBrand(long brandId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM CarModel WHERE carBrand.id = " + brandId, CarModel.class).list();
    }
}
