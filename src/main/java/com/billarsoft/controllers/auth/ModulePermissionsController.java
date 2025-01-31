package com.billarsoft.controllers.auth;


import com.billarsoft.controllers.dto.ModulePermissionsDTO;
import com.billarsoft.models.auth.Module;
import com.billarsoft.models.auth.ModulePermissions;
import com.billarsoft.models.auth.ModulePermissionsId;
import com.billarsoft.models.auth.Role;
import com.billarsoft.services.auth.IModuleService;
import com.billarsoft.services.auth.Impl.IModulePermissionsServiceImpl;
import com.billarsoft.services.auth.Impl.ModuleServiceImpl;
import com.billarsoft.services.auth.Impl.RoleServiceImpl;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("module_permissions")
public class ModulePermissionsController {


    @Autowired
    private IModulePermissionsServiceImpl modulePermissionsService;
    @Autowired
    private ModuleServiceImpl moduleService;

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getModulePermissionsByRoleId(@PathVariable Integer id) {
        List<ModulePermissions> modulePermissions = modulePermissionsService.getModulePermissionsByRoleId(id);
        List<ModulePermissionsDTO> modulePermissionsDTO = modulePermissions.stream().map(
                modulePermissionsToMap -> ModulePermissionsDTO.builder()
                        .roleId(modulePermissionsToMap.getModulePermissionsId().getRoleId())
                        .moduleId(modulePermissionsToMap.getModulePermissionsId().getModuleId())
                        .moduleName(moduleService.getModuleById(
                                modulePermissionsToMap.getModulePermissionsId().getModuleId()
                        ).get().getName())
                        .list(modulePermissionsToMap.getListt())
                        .insert(modulePermissionsToMap.getInsertt())
                        .update(modulePermissionsToMap.getUpdatee())
                        .delete(modulePermissionsToMap.getDeletee())
                        .build()).toList();
        return ResponseEntity.ok(modulePermissionsDTO);
    }


    @PostMapping
    public ResponseEntity<?> insertModulePermissions(@RequestBody ModulePermissionsDTO modulePermissionsDTO) {


        Optional<Module> moduleIfExist = moduleService.getModuleById(modulePermissionsDTO.getModuleId());
        Optional<Role> roleIfExist = roleService.getRole(modulePermissionsDTO.getRoleId());

        Module module = Module.builder()
                .id(moduleIfExist.get().getId())
                .name(moduleIfExist.get().getName())
                .description(moduleIfExist.get().getDescription())
                .build();

        Role role = Role.builder()
                .id(roleIfExist.get().getId())
                .name(roleIfExist.get().getName())
                .description(roleIfExist.get().getDescription())
                .build();
        ModulePermissionsId modulePermissionsId = ModulePermissionsId.builder()
                .moduleId(module.getId())
                .roleId(role.getId())
                .build();

        ModulePermissions modulePermissions = ModulePermissions.builder()
                .listt(modulePermissionsDTO.getList())
                .insertt(modulePermissionsDTO.getInsert())
                .updatee(modulePermissionsDTO.getUpdate())
                .deletee(modulePermissionsDTO.getDelete())
                .module(module)
                .role(role)
                .modulePermissionsId(modulePermissionsId)
                .build();
        boolean responseService = modulePermissionsService.insertModulePermissions(modulePermissions);
        if (responseService) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/list")
    public ResponseEntity<?> insertModulesPermissions(@RequestBody List<ModulePermissionsDTO> modulesPermissionsDTO) {

        List<ModulePermissions> modulePermissionsList = new ArrayList<>();

        for (ModulePermissionsDTO dto : modulesPermissionsDTO) {
            Optional<Module> moduleIfExist = moduleService.getModuleById(dto.getModuleId());
            if (moduleIfExist.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<Role> roleIfExist = roleService.getRole(dto.getRoleId());
            if (roleIfExist.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Module module = Module.builder()
                    .id(moduleIfExist.get().getId())
                    .name(moduleIfExist.get().getName())
                    .description(moduleIfExist.get().getDescription())
                    .build();

            Role role = Role.builder()
                    .id(roleIfExist.get().getId())
                    .name(roleIfExist.get().getName())
                    .description(roleIfExist.get().getDescription())
                    .build();

            ModulePermissionsId modulePermissionsId = ModulePermissionsId.builder()
                    .moduleId(module.getId())
                    .roleId(role.getId())
                    .build();

            ModulePermissions modulePermissions = ModulePermissions.builder()
                    .listt(true)
                    .insertt(false)
                    .updatee(false)
                    .deletee(false)
                    .module(module)
                    .role(role)
                    .modulePermissionsId(modulePermissionsId)
                    .build();

            modulePermissionsList.add(modulePermissions);
            modulePermissionsService.insertModulesPermissions(modulePermissionsList);
        }

        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<?> patchModulePermissions(@RequestBody ModulePermissionsDTO modulePermissionsDTO) {

        Optional<Role> role = roleService.getRole(modulePermissionsDTO.getRoleId());
        Optional<Module> module = moduleService.getModuleById(modulePermissionsDTO.getModuleId());

        if (role.isPresent() && module.isPresent()) {
            ModulePermissionsId modulePermissionsId = ModulePermissionsId.builder()
                    .roleId(role.get().getId())
                    .moduleId(module.get().getId())
                    .build();

            ModulePermissions modulePermissionsToUpdate = ModulePermissions.builder()
                    .role(role.get())
                    .module(module.get())
                    .modulePermissionsId(modulePermissionsId)
                    .listt(modulePermissionsDTO.getList())
                    .insertt(modulePermissionsDTO.getInsert())
                    .updatee(modulePermissionsDTO.getUpdate())
                    .deletee(modulePermissionsDTO.getDelete())
                    .build();
            boolean responseService = modulePermissionsService.updateModulePermissions(modulePermissionsToUpdate);
            if (responseService) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping
    public ResponseEntity<?> deleteModulePermissions(@RequestBody ModulePermissionsDTO modulePermissionsDTO) {
        ModulePermissionsId modulePermissionsId = ModulePermissionsId.builder()
                .moduleId(modulePermissionsDTO.getModuleId())
                .roleId(modulePermissionsDTO.getRoleId())
                .build();
        boolean responseService = modulePermissionsService.deleteModulePermissions(modulePermissionsId);
        if (responseService) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("permissionsId")
    public ResponseEntity<?> getPermissionsByModulePermissionsId(@RequestBody ModulePermissionsId modulePermissionsId) {
        ModulePermissions modulePermissions = modulePermissionsService.
                getPermissionsByModulePermissionsId(modulePermissionsId);
        if (modulePermissions != null) {
            return ResponseEntity.ok(modulePermissions);

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
