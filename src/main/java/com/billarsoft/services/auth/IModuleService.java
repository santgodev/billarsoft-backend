package com.billarsoft.services.auth;

import com.billarsoft.models.auth.Module;

import java.util.List;
import java.util.Optional;

public interface IModuleService {

    List<Module> getModulesAndComponentsByRoleIdWithPermissionsToList2(Integer id);

    Optional<Module> getModuleById(Integer id);

    List<Module> getModules();

    List<Module> getModulesWhereRoleIdNot(Integer id);

    boolean insertModule(Module module);

    boolean deleteModule(Integer id);

    boolean updateModule(Module module);
}
