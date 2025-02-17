package com.billarsoft.controllers.auth;


import com.billarsoft.controllers.auth.DTO.ModuleDTO;
import com.billarsoft.services.auth.Impl.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.billarsoft.models.auth.Module;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("modules")
public class ModuleController {

    @Autowired
    private ModuleServiceImpl moduleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Integer id) {
        Optional<Module> module = moduleService.getModuleById(id);
        if (module.isPresent()) {
            Module modulePresent = module.get();
            ModuleDTO moduleDTO = ModuleDTO.builder()
                    .id(modulePresent.getId())
                    .name(modulePresent.getName())
                    .description(modulePresent.getDescription())
                    .build();
            return ResponseEntity.ok(moduleDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> getModules() {
        List<Module> modules = moduleService.getModules();
        List<ModuleDTO> modulesDTO = modules.stream().map(module -> ModuleDTO.builder()
                .id(module.getId())
                .name(module.getName())
                .description(module.getDescription())
                .build()).toList();
        return ResponseEntity.ok(modulesDTO);
    }
    @GetMapping("/role/{id}")
    public ResponseEntity<?> getModulesByRoleId(@PathVariable Integer id) {
        List<Module> modules = moduleService.getModules();
            List<Module> modulesWithComponents = moduleService.getModulesAndComponentsByRoleIdWithPermissionsToList2(id);
            return ResponseEntity.ok(modulesWithComponents);
    }
    @GetMapping("/rolenot/{id}")
    public ResponseEntity<?> getModulesByRoleIdNot(@PathVariable Integer id) {
        List<Module> modules = moduleService.getModulesWhereRoleIdNot(id);
        return ResponseEntity.ok(modules);
    }

    @PostMapping
    public ResponseEntity<?> insertModule(@RequestBody ModuleDTO moduleDTO) {
        Module module = Module.builder()
                .name(moduleDTO.getName())
                .description(moduleDTO.getDescription())
                .build();

        Boolean responseService = moduleService.insertModule(module);
        if (responseService) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(module.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Integer id) {
        Boolean reponseService = moduleService.deleteModule(id);
        if (reponseService) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> updateModule(@RequestBody ModuleDTO moduleDTO) {
        Module module = Module.builder()
                .id(moduleDTO.getId())
                .name(moduleDTO.getName())
                .description(moduleDTO.getDescription())
                .build();
        Boolean responseService = moduleService.updateModule(module);
        if (responseService) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
