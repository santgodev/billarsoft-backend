package com.billarsoft.services.auth.Impl;

import com.billarsoft.models.auth.User;
import com.billarsoft.repositorys.auth.IUserRepository;
import com.billarsoft.services.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    public IUserRepository userRepository;


    @Override
    public Boolean login(String email, String passwordToLogin) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String passwordInDB = user.get().getPassword();
            if (passwordToLogin.equals(passwordInDB)) {
                return true;
            }
        }
        return false;
    }


}
