package com.billarsoft.services.auth;

import com.billarsoft.models.auth.Components;

import java.util.List;

public interface IComponentService {
    List<Components> getComponents();

    List<Components> getComponentsByModuleId(Integer id);
}
