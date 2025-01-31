package com.billarsoft.repositorys.auth;

import com.billarsoft.models.auth.ModulePermissions;
import com.billarsoft.models.auth.ModulePermissionsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IModulePermissionsRepository extends JpaRepository<ModulePermissions, ModulePermissionsId> {


     List<ModulePermissions> findByModulePermissionsIdRoleId(Integer roleId);

     ModulePermissions findByModulePermissionsId(ModulePermissionsId modulePermissionsId);

}
