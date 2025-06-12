package com.colab.backend.service;

import com.colab.backend.model.User;
import com.colab.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
