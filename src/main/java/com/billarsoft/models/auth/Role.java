package com.billarsoft.models.auth;


import com.billarsoft.models.bussines.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "roles", schema = "auth")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(targetEntity = User.class,mappedBy = "role" ,fetch = FetchType.LAZY)
    private List<User> users;

    @JsonIgnore
    @OneToMany(targetEntity = ModulePermissions.class,mappedBy = "role" ,fetch = FetchType.LAZY)
    private List<ModulePermissions> modulePermissions;

    @ManyToOne( targetEntity = Role.class)
    @JoinColumn(name = "client_id")
    private Client client;


}
