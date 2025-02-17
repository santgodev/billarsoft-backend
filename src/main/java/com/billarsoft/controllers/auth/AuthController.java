package com.billarsoft.controllers.auth;


import com.billarsoft.controllers.auth.DTO.UserDTO;
import com.billarsoft.models.auth.User;
import com.billarsoft.services.auth.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private IModulePermissionsServiceImpl modulePermissionsService;

    @Autowired
    private ModuleServiceImpl moduleService;


    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Boolean responseLogin = authService.login(email, password);

        if (responseLogin) {
            User user = userService.getUserByEmail(email);
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getName())
                    .password(user.getPassword())
                    .roleId(user.getRole().getId())
                    .build();
            return ResponseEntity.ok(userDTO);

        }

        return ResponseEntity.badRequest().build();
    }

}
