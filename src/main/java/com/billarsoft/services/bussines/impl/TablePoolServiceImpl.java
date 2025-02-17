package com.billarsoft.services.bussines.impl;

import com.billarsoft.models.bussines.TablePool;
import com.billarsoft.repositorys.bussines.ITablePoolRepository;
import com.billarsoft.services.bussines.ITablePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import com.billarsoft.controllers.bussines.DTO.TablePoolDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TablePoolServiceImpl implements ITablePoolService {
    @Autowired
    private ITablePoolRepository tablePoolRepository;

    // Convertir TablePool a TablePoolDTO
    private TablePoolDTO convertToDTO(TablePool tablePool) {
        return TablePoolDTO.builder()
                .id(tablePool.getId())
                .name(tablePool.getName())
                .status(tablePool.getStatus())
                .build();
    }

    // Convertir lista de TablePool a lista de TablePoolDTO
    private List<TablePoolDTO> convertToDTOList(List<TablePool> tablePools) {
        return tablePools.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    // Obtener todas las mesas
    @Override
    public List<TablePool> getAllTables() {
        return tablePoolRepository.findAll();
    }

    // Obtener mesas por cliente
    @Override
    public List<TablePool> getTablesByClientId(Integer clientId) {
        return tablePoolRepository.findByClientId(clientId);
    }

    // Obtener una mesa por ID
    @Override
    public Optional<TablePool> getTableById(Integer id) {
        return tablePoolRepository.findById(id);
    }

    // Crear una nueva mesa
    @Override
    public TablePool createTable(TablePool tablePool) {
        return tablePoolRepository.save(tablePool);
    }

    // Actualizar una mesa existente
    @Override
    public Optional<TablePool> updateTable(Integer id, TablePool tablePool) {
        if (tablePoolRepository.existsById(id)) {
            tablePool.setId(id);
            return Optional.of(tablePoolRepository.save(tablePool));
        }
        return Optional.empty();
    }

    // Eliminar una mesa
    @Override
    public void deleteTable(Integer id) {
        tablePoolRepository.deleteById(id);
    }
}
