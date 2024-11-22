package com.myproject.journalApp.controller;


import com.myproject.journalApp.entity.User;
import com.myproject.journalApp.srevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User myEntry) {
        try {
            userService.addUser(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserByiId(@PathVariable String id) {
        try {
            userService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateByID(@PathVariable String id, @RequestBody User myEntry1) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            user.setUserName(user.getUserName() != null && !user.getUserName().equals("") ? myEntry1.getUserName() : user.getUserName());
            user.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? myEntry1.getPassword() : user.getPassword());
            return new ResponseEntity<>(myEntry1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public Optional<User> findById(@PathVariable String id) {
        return userService.findById(id);
    }

}
