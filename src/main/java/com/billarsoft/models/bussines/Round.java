package com.billarsoft.models.bussines;

import jakarta.persistence.*;

@Entity
@Table(name = "rounds", schema = "business")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_pool_id", nullable = false)
    private TablePool tablePool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loser_id", nullable = false)
    private Player loser;
}
