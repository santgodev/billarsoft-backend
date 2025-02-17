package com.billarsoft.services.bussines.impl;

import com.billarsoft.models.auth.User;
import com.billarsoft.models.bussines.Client;
import com.billarsoft.controllers.bussines.DTO.ClientDTO;
import com.billarsoft.repositorys.bussines.IClientRepository;
import com.billarsoft.services.bussines.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    // Método para convertir de Client a ClientDTO
    private ClientDTO convertToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .address(client.getAddress())
                .phone1(client.getPhone1())
                .phone2(client.getPhone2())
                .municipality(client.getMunicipality())
                .isActive(client.getIsActive())
                .build();
    }

    // Método para convertir de una lista de Client a lista de ClientDTO
    private List<ClientDTO> convertToDTOList(List<Client> clients) {
        return clients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener todos los clientes
    public List<Client> getClients() {
        List<Client> clients = clientRepository.findAll();
        return (clientRepository.findAll() != null) ? clientRepository.findAll() : Collections.emptyList();
    }


    // Obtener cliente por ID
    public Optional<Client> getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }

    // Insertar un nuevo cliente
    public boolean insertClient(Client client) {
        try {
            clientRepository.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Eliminar un cliente
    public boolean deleteClient(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar un cliente
    public boolean updateClient(Client client) {
        if (clientRepository.existsById(client.getId())) {
            clientRepository.save(client);
            return true;
        }
        return false;
    }
}
