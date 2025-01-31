package com.billarsoft.services.auth.Impl;

import com.billarsoft.models.auth.Role;
import com.billarsoft.models.auth.User;
import com.billarsoft.repositorys.auth.IRoleRepository;
import com.billarsoft.repositorys.auth.IUserRepository;
import com.billarsoft.services.auth.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public Optional<User> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return (userRepository.findAll() != null) ? userRepository.findAll() : Collections.emptyList();
    }

    @Override
    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public Role getUserRole(Integer id) {
        Role role = roleRepository.findByUsersId(id);
        return role;
    }

    @Override
    public boolean insertUser(User user) {
        if (user.getName().isBlank() || user.getEmail().isBlank() || user.getPassword().isBlank()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }


    @Override
    public boolean deleteUser(Integer id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isPresent()) {
            userRepository.delete(userToDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean putUser(User user) {
        Optional<User> userInDB = userRepository.findById(user.getId());
        if (userInDB.isPresent()) {
            User userToUpdate = User.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .role(user.getRole())
                    .password(user.getPassword())
                    .build();
            userRepository.save(user);
            return true;
        }
        return false;
    }


}
