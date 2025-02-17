package com.billarsoft.services.bussines;

import com.billarsoft.models.bussines.TablePool;

import java.util.List;
import java.util.Optional;

public interface ITablePoolService {

    List<TablePool> getAllTables();

    List<TablePool> getTablesByClientId(Integer clientId);

    Optional<TablePool> getTableById(Integer id);

    TablePool createTable(TablePool tablePool);

    Optional<TablePool> updateTable(Integer id, TablePool tablePool);

    void deleteTable(Integer id);
}
