package com.billarsoft.controllers.bussines.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TablePoolDTO {

    private Integer id;
    private String status;
    private String name;
    private Integer clientId;
}
