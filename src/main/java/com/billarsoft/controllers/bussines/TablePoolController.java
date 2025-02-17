package com.billarsoft.controllers.bussines;

import com.billarsoft.models.bussines.TablePool;
import com.billarsoft.services.bussines.impl.ClientServiceImpl;
import com.billarsoft.services.bussines.impl.TablePoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tables")
public class TablePoolController {

    @Autowired
    private TablePoolServiceImpl tablePoolService;

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Optional<TablePool> serviceResponse = tablePoolService.getTableById(id);
        if (serviceResponse.isPresent()) {
            return ResponseEntity.ok(serviceResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TablePool> serviceResponse = tablePoolService.getAllTables();
        return ResponseEntity.ok(serviceResponse);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getByClient(@PathVariable Integer clientId) {
        List<TablePool> serviceResponse = tablePoolService.getTablesByClientId(clientId);
        return ResponseEntity.ok(serviceResponse);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody TablePool tablePool) {
        tablePool.setStatus("cerrada");
        tablePool.setClient(clientService.getClientById(tablePool.getClient().getId()).get());
        TablePool createdTable = tablePoolService.createTable(tablePool);
        return ResponseEntity.ok(createdTable);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TablePool tablePool) {
        Optional<TablePool> serviceResponse = tablePoolService.updateTable(tablePool.getId(), tablePool);
        if (serviceResponse.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        tablePoolService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
