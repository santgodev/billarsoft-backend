package com.billarsoft.services.auth;

import com.billarsoft.models.auth.Role;
import com.billarsoft.models.auth.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(Integer id);

    List<User> getUsers();

    User getUserByEmail(String email);

    Role getUserRole(Integer id);

    boolean insertUser(User user);

    boolean deleteUser(Integer id);

    boolean putUser(User userToUpdate);
}
