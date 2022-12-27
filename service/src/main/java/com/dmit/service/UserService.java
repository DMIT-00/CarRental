package com.dmit.service;

import com.dmit.dao.UserDao;
import com.dmit.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(User user) {
        //TODO: checks
        user.setId(null);
        user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
}
