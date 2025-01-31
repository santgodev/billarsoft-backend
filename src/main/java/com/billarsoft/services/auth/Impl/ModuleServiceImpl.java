package com.billarsoft.services.auth.Impl;

import com.billarsoft.repositorys.auth.IModuleRepository;
import com.billarsoft.services.auth.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.billarsoft.models.auth.Module;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private IModuleRepository moduleRepository;


    @Override
    public List<Module> getModulesAndComponentsByRoleIdWithPermissionsToList2(Integer id) {
        return moduleRepository.findByModulePermissionsModulePermissionsIdRoleIdAndModulePermissionsListtTrue(id);
    }

    @Override
    public Optional<Module> getModuleById(Integer id) {
        return moduleRepository.findById(id);
    }

    @Override
    public List<Module> getModules() {
        return (moduleRepository.findAll() != null) ? moduleRepository.findAll() : Collections.emptyList();
    }

    @Override
    public List<Module> getModulesWhereRoleIdNot(Integer id) {
                List<Module> modules = moduleRepository.findModulesWithoutPermissionsForRole(id);
                return  modules;
    }

    @Override
    public boolean insertModule(Module module) {
        if (module.getName() != null) {
            moduleRepository.save(module);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteModule(Integer id) {
        Optional<Module> moduleToDelete = moduleRepository.findById(id);
        if (moduleToDelete.isPresent()) {
            moduleRepository.delete(moduleToDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateModule(Module module) {
        Optional<Module> moduleInDB = moduleRepository.findById(module.getId());
        if (moduleInDB.isPresent()) {
            Module moduleToUpdate = Module.builder()
                    .id(module.getId())
                    .name(module.getName())
                    .description(module.getDescription())
                    .build();
            moduleRepository.save(moduleToUpdate);
            return true;
        }
        return false;
    }

}


