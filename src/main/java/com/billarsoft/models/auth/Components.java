package com.billarsoft.models.auth;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "components", schema = "auth")
@Entity
public class Components {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
    private String icon;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "module_id")
    private Module module;


}
