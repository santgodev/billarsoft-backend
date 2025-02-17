package com.billarsoft.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "modules_permissions", schema = "auth")
@Entity
public class ModulePermissions {


    @EmbeddedId()
    private ModulePermissionsId modulePermissionsId;

    @Column (name = "list")
    private Boolean list;
    @Column (name = "insert")
    private Boolean insert;
    @Column (name = "update")
    private Boolean update;
    @Column (name = "delete")
    private Boolean delete;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("moduleId")
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;






}

