package com.billarsoft.repositorys.bussines;

import com.billarsoft.models.bussines.TablePool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITablePoolRepository extends JpaRepository<TablePool, Integer> {

    List<TablePool> findByClientId(Integer clientId);
}
