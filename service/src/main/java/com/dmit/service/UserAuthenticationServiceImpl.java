package com.dmit.service;

import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserAuthenticationDto;
import com.dmit.entity.user.User;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserAuthenticationDto findUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found! Name: " + username);

        return modelMapper.map(user, UserAuthenticationDto.class);
    }
}
