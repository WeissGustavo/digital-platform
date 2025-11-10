/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.service;

import br.com.banking.digital_platform.dao.UserDAO;
import br.com.banking.digital_platform.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class UserService {
    
    @Autowired
    private UserDAO userDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDao.findAll(User.class);
    }

    public void deleteUser(Long id) {
        userDao.delete(id,User.class);
    }

    public User updateUser(User user) {
       return userDao.update(user);
    }

    public User getUserById(Long id) {
        return userDao.findById(id,User.class);
    }

    public User registerUser(User user) {
        return userDao.save(user);
    }
}
