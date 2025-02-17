package com.billarsoft.controllers.bussines;

import com.billarsoft.models.bussines.Client;
import com.billarsoft.services.bussines.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Optional<Client> serviceResponse = clientService.getClientById(id);
        if (serviceResponse.isPresent()) {
            return ResponseEntity.ok(serviceResponse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Client> serviceResponse = clientService.getClients();
        return ResponseEntity.ok(serviceResponse);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Client client) {
        client.setRegistrationDate(LocalDateTime.now());
        boolean responseService = clientService.insertClient(client);
        return responseService ? ResponseEntity.ok(client) : ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Client client) {
        boolean responseService = clientService.updateClient(client);
        return responseService ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean responseService = clientService.deleteClient(id);
        return responseService ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
