package com.myproject.journalApp.srevice;

import com.myproject.journalApp.controller.UserController;
import com.myproject.journalApp.entity.User;
import com.myproject.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepo;



    public void addUser(User myEntry) {
        userRepo.save(myEntry);
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    public void getById(String id) {
        userRepo.findById(id);
    }

    public Optional<User> findById(String id) {
        return userRepo.findById(id);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
}
