package com.billarsoft.services.auth.Impl;

import com.billarsoft.models.auth.Role;
import com.billarsoft.repositorys.auth.IRoleRepository;
import com.billarsoft.services.auth.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> getRole(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getRoles() {
        return (roleRepository.findAll()!=null) ? roleRepository.findAll() : Collections.emptyList();
    }

    @Override
    public void insertRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public boolean deleteRole(Integer id) {
        Optional<Role> roleToDelete = roleRepository.findById(id);
        roleRepository.deleteById(id);

        if (roleToDelete.isPresent()) {
            roleRepository.delete(roleToDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        Optional<Role> roleExistOrNot = roleRepository.findById(role.getId());
        if (roleExistOrNot.isPresent()) {
            Role roleToUpdate = Role.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .description(role.getDescription())
                    .build();
            roleRepository.save(roleToUpdate);
            return true;
        }
        return false;

    }


}
