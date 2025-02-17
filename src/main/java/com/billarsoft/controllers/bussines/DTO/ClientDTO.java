package com.billarsoft.controllers.bussines.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone1;
    private String phone2;
    private String municipality;
    private Boolean isActive;
}
