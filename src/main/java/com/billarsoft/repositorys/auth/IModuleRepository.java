package com.billarsoft.repositorys.auth;
import com.billarsoft.models.auth.Module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IModuleRepository extends JpaRepository<Module, Integer> {

    //este lo dejo de ejemplo pero esta muy largo el nombre para ser legible
    List<Module> findByModulePermissionsModulePermissionsIdRoleIdAndModulePermissionsListtTrue(Integer id);

    @Query("select m from Module m join m.modulePermissions mp join mp.role r where r.id = :id ")
    List<Module> findModulesWithPermissionsToListByRoleId(@Param("id") Integer id);

    @Query("SELECT m FROM Module m " +
            "WHERE m.id NOT IN (" +
            "  SELECT mp.modulePermissionsId.moduleId " +
            "  FROM ModulePermissions mp " +
            "  WHERE mp.modulePermissionsId.roleId = :roleId" +
            ")")
    List<Module> findModulesWithoutPermissionsForRole(@Param("roleId") Integer roleId);}
