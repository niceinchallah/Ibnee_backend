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
@Table(name = "Signatures")
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type; // "dessiner", "saisir", "importer"

    @Column(name = "content", columnDefinition = "BLOB")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = true)
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = true)
    private Facture facture;
}
