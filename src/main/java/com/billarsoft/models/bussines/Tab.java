package com.billarsoft.models.bussines;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.lang.model.element.Name;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tab", schema = "business")
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private Boolean isOpen = true;

    private LocalDateTime openedAt = LocalDateTime.now();

    private LocalDateTime closedAt;



    @ManyToOne
    @JoinColumn( name = "table_pool_id", nullable = false)
    private TablePool tablePool;

    @JsonIgnore
    @OneToMany(mappedBy = "tab", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;
}
