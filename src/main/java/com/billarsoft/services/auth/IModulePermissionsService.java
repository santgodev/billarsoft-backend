package com.billarsoft.services.auth;

import com.billarsoft.models.auth.Module;
import com.billarsoft.models.auth.ModulePermissions;
import com.billarsoft.models.auth.ModulePermissionsId;

import java.util.List;
import java.util.Optional;

public interface IModulePermissionsService {

    List<ModulePermissions> getModulePermissionsByRoleId(Integer id);

    boolean insertModulePermissions(ModulePermissions modulePermissions);

    boolean deleteModulePermissions(ModulePermissionsId modulePermissionsId);

    boolean insertModulesPermissions(List<ModulePermissions> modulesPermissions);

    boolean updateModulePermissions(ModulePermissions modulePermissions);



    ModulePermissions   getPermissionsByModulePermissionsId(ModulePermissionsId modulePermissionsId);
}
