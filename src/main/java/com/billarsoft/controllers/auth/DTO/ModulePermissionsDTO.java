package com.billarsoft.controllers.auth.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModulePermissionsDTO {
    private Integer roleId;
    private Integer moduleId;
    private String moduleName;
    private Boolean list;
    private Boolean insert;
    private Boolean update;
    private Boolean delete;

}
