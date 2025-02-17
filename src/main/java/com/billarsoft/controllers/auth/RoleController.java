package com.billarsoft.controllers.auth;


import com.billarsoft.controllers.auth.DTO.RoleDTO;
import com.billarsoft.models.auth.Role;
import com.billarsoft.services.auth.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("roles")
@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
        Optional<Role> role = roleServiceImpl.getRole(id);
        if (role.isPresent()) {
            Role roleTemp = role.get();
            RoleDTO roleDTO = RoleDTO.builder()
                    .name(roleTemp.getName()).
                    description(roleTemp.getDescription())
                    .build();
            return ResponseEntity.ok(roleDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getRoles() {
        List<RoleDTO> rolesDTO = roleServiceImpl.getRoles().stream()
                .map(role -> RoleDTO.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .description(role.getDescription())
                        .build()).toList();
        return ResponseEntity.ok(rolesDTO);
    }

    @PostMapping
    public ResponseEntity<?> insertRole(@RequestBody RoleDTO roleDTO) {
        if (roleDTO.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Role role = Role.builder()
                .name(roleDTO.getName())
                .description(roleDTO.getDescription())
                .build();
        roleServiceImpl.insertRole(role);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<?> putRole(@RequestBody Role role) {
        Boolean response = roleServiceImpl.updateRole(role);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        Boolean responseService = roleServiceImpl.deleteRole(id);
        if (responseService) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
