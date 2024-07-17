package com.test.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Impots")
public class Impot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private double percentage;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = true)
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = true)
    private Facture facture;
}

