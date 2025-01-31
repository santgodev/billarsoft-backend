package com.billarsoft.models.auth;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModulePermissionsId implements Serializable {

    private Integer moduleId;
    private Integer roleId;
}
