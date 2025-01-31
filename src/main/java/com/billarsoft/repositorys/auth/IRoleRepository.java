package com.billarsoft.repositorys.auth;


import com.billarsoft.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByUsersId(Integer id);

}
