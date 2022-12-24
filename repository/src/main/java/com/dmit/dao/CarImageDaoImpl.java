package com.dmit.dao;

import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class CarImageDaoImpl implements CarImageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Image object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public Image read(Image object) {
        return findById(object.getId());
    }

    @Override
    public void update(Image object) {
        create(object);
    }

    @Override
    public void delete(Image object) {
        Image loadedObject = sessionFactory.getCurrentSession().load(Image.class, object.getId());
        sessionFactory.getCurrentSession().delete(loadedObject);
    }

    @Override
    public Image findById(UUID id) {
        return sessionFactory.getCurrentSession().get(Image.class, id);
    }

    @Override
    public List<Image> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Image", Image.class).list();
    }
}
