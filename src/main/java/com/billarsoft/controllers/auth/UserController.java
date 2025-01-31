package com.billarsoft.controllers.auth;


import com.billarsoft.controllers.dto.UserDTO;
import com.billarsoft.models.auth.User;
import com.billarsoft.repositorys.auth.IRoleRepository;
import com.billarsoft.services.auth.Impl.RoleServiceImpl;
import com.billarsoft.services.auth.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleServiceImpl roleService;


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Optional<User> serviceResponse = userServiceImpl.getUserById(id);
        if (serviceResponse.isPresent()) {
            return ResponseEntity.ok(serviceResponse);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> serviceResponse = userServiceImpl.getUsers();
        return ResponseEntity.ok(serviceResponse);
    }


    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(roleService.getRole(userDTO.getRoleId()).get())
                .build();
        Boolean responseService = userServiceImpl.insertUser(user);
        if (responseService) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<?> put(@RequestBody UserDTO userDTO) {
        User user1 = User.builder()
                .role(roleService.getRole(userDTO.getRoleId()).get())
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        userServiceImpl.putUser(user1);
        return ResponseEntity.noContent().build();
    }


}
