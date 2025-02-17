package com.billarsoft.controllers.auth.DTO;

import com.billarsoft.models.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer roleId;
    private Optional<Role> role;
}