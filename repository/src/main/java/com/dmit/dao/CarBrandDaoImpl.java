package com.dmit.dao;

import com.dmit.entity.car.CarBrand;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CarBrandDaoImpl implements CarBrandDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(CarBrand object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public CarBrand read(CarBrand object) {
        return findById(object.getId());
    }

    @Override
    public void update(CarBrand object) {
        create(object);
    }

    @Override
    public void delete(CarBrand object) {
        CarBrand loadedObject = sessionFactory.getCurrentSession().load(CarBrand.class, object.getId());
        sessionFactory.getCurrentSession().delete(loadedObject);
    }

    @Override
    public CarBrand findById(Long id) {
        return sessionFactory.getCurrentSession().get(CarBrand.class, id);
    }

    @Override
    public List<CarBrand> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from CarBrand", CarBrand.class).list();
    }
}
