package com.billarsoft.services.auth.Impl;

import com.billarsoft.models.auth.ModulePermissions;
import com.billarsoft.models.auth.ModulePermissionsId;
import com.billarsoft.repositorys.auth.IModulePermissionsRepository;
import com.billarsoft.services.auth.IModulePermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class IModulePermissionsServiceImpl implements IModulePermissionsService {

    @Autowired
    private IModulePermissionsRepository modulePermissionsRepository;


    @Override
    public List<ModulePermissions> getModulePermissionsByRoleId(Integer id) {
        List<ModulePermissions> modulePermissions = modulePermissionsRepository.findByModulePermissionsIdRoleId(id);
        if (modulePermissions == null) {
            return Collections.emptyList();
        }
        return modulePermissions;
    }

    @Override
    public boolean insertModulePermissions(ModulePermissions modulePermissions) {
        if (modulePermissions.getModulePermissionsId() != null) {
            modulePermissionsRepository.save(modulePermissions);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertModulesPermissions(List<ModulePermissions> modulesPermissions) {
        if (modulesPermissions.isEmpty()) {
            return false;
        }
        modulePermissionsRepository.saveAll(modulesPermissions);
        return true;
    }


    @Override
    public boolean updateModulePermissions(ModulePermissions modulePermissions) {
        Optional<ModulePermissions> modulePermissionsInDB = modulePermissionsRepository
                .findById(modulePermissions.getModulePermissionsId());
        if (modulePermissionsInDB.isPresent()) {
            ModulePermissions modulePermissionsToUpdate = ModulePermissions.builder()
                    .modulePermissionsId(modulePermissionsInDB.get().getModulePermissionsId())
                    .build();
            if (modulePermissions.getListt() != null) {
                modulePermissionsToUpdate.setListt(modulePermissions.getListt());
            }
            if (modulePermissions.getInsertt() != null) {
                modulePermissionsToUpdate.setInsertt(modulePermissions.getInsertt());
            }

            if (modulePermissions.getUpdatee() != null) {
                modulePermissionsToUpdate.setUpdatee(modulePermissions.getUpdatee());
            }

            if (modulePermissions.getDeletee() != null) {
                modulePermissionsToUpdate.setDeletee(modulePermissions.getDeletee());
            }
            modulePermissionsRepository.save(modulePermissions);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteModulePermissions(ModulePermissionsId modulePermissionsId) {
        Optional<ModulePermissions> modulePermissionsToDelete = modulePermissionsRepository.findById(modulePermissionsId);
        if (modulePermissionsToDelete.isPresent()) {
            modulePermissionsRepository.deleteById(modulePermissionsId);
            return true;
        }
        return false;
    }

    @Override
    public ModulePermissions   getPermissionsByModulePermissionsId(ModulePermissionsId modulePermissionsId){
        ModulePermissions modulePermissions = modulePermissionsRepository.findByModulePermissionsId(modulePermissionsId);
        if (modulePermissions == null) {
        }
        return modulePermissions;
    }
}
