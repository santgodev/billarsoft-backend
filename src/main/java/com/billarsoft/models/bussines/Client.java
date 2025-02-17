package com.billarsoft.models.bussines;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "clients", schema = "business")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    @Column(name = "phone1", length = 15)
    private String phone1;

    @Column(name = "phone2", length = 15)
    private String phone2;

    private String municipality;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "registration_date", updatable = false, nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();


}
