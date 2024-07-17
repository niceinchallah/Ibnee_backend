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
@Table(name = "InformationBasDePage")
public class InformationBasDePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "additionnel")
    private String additionnel;

    @Column(name = "companyDetails")
    private String companyDetails;

    @Column(name = "contactDetails")
    private String contactDetails;

    @Column(name = "bankDetails")
    private String bankDetails;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = true)
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = true)
    private Facture facture;
}
