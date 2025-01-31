package com.billarsoft.services.auth;

import com.billarsoft.models.auth.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Optional<Role> getRole(Integer id);

    List<Role> getRoles();

    void insertRole(Role role);

    boolean deleteRole(Integer id);

    boolean updateRole(Role role);
}
