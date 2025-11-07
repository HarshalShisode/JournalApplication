package com.harshal.learning.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.harshal.learning.entity.User;
import com.harshal.learning.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    
    //no need to creat everytime
    //private static final Logger logger=LoggerFactory.getLogger(UserService.class);

    public void saveUserEntry(User user) {
        userRepository.save(user);
    }

    public void saveNewUserEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("error occured during saving", e);
        }
        
    }

    public List<User> getAllEntries() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id); 
    }

    public User updateUserEntryById(ObjectId id, User newUser) {
        User oldUser=userRepository.findById(id).orElse(null);
        if(oldUser!=null){
            oldUser.setUserName(newUser.getUserName()!=null&&!newUser.getUserName().equals("")?newUser.getUserName():oldUser.getUserName());
            oldUser.setPassword(newUser.getPassword()!=null&&!newUser.getPassword().equals("")?newUser.getPassword():oldUser.getPassword());
        }
        return userRepository.save(oldUser);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
