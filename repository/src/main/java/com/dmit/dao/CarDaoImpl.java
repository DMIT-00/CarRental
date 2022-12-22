package com.dmit.dao;

import com.dmit.entity.car.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class CarDaoImpl implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Car object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public Car read(Car object) {
        return findById(object.getId());
    }

    @Override
    public void update(Car object) {
        create(object);
    }

    @Override
    public void delete(Car object) {
        Car loadedObject = sessionFactory.getCurrentSession().load(Car.class, object.getId());
        sessionFactory.getCurrentSession().delete(loadedObject);
    }

    @Override
    public Car findById(UUID id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Car", Car.class).list();
    }
}
