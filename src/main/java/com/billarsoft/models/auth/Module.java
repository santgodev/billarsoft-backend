package com.billarsoft.models.auth;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "modules", schema = "auth")
@Entity
public class Module {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String description;

        @JsonIgnore
        @OneToMany(targetEntity = ModulePermissions.class,mappedBy = "module" ,fetch = FetchType.LAZY)
        private List<ModulePermissions> modulePermissions;

        @OneToMany(targetEntity = Components.class, mappedBy = "module", fetch = FetchType.LAZY)
        private  List<Components> components;


}
