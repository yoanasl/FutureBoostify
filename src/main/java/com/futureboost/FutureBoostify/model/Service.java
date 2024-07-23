package com.futureboost.FutureBoostify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Name of the service

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign; // Reference to the associated campaign

    private Integer cost; // Cost associated with the service

    // Getters and Setters
}
