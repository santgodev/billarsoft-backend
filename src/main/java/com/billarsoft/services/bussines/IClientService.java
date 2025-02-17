package com.billarsoft.services.bussines;

import com.billarsoft.models.bussines.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Optional<Client> getClientById(Integer id);

    List<Client> getClients();

    boolean insertClient(Client client);

    boolean deleteClient(Integer id);

    boolean updateClient(Client client);
}
