package com.billarsoft.repositorys.bussines;

import com.billarsoft.models.bussines.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {

}
