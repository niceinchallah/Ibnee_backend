package com.test.test.DTO;

import com.test.test.entity.Facture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactureDTO {
    private Long id;
    private String numeroDeFacture;
    private LocalDate dateDeFacture;
    private LocalDate dateEcheance;
    private Long clientId;
    private Long entrepriseId;
    private List<ArticleDTO> articles;
    private List<ImpotDTO> impots;
    private List<InformationBasDePageDTO> informationsBasDePage;
    private List<SignatureDTO> signatures;
    private List<PDFStorageDTO> pdfs;
    private boolean draft;
    private byte[] pdfFile;
    private double totalAmount;

    public FactureDTO(Facture facture) {
        this.id = facture.getId();
        this.numeroDeFacture = facture.getNumeroDeFacture();
        this.dateDeFacture = facture.getDateDeFacture();
        this.dateEcheance = facture.getDateDEcheance();
        this.clientId = facture.getClient().getId();
        this.entrepriseId = facture.getEntreprise().getId();
        this.articles = facture.getArticles().stream().map(ArticleDTO::new).collect(Collectors.toList());
        this.impots = facture.getImpots().stream().map(ImpotDTO::new).collect(Collectors.toList());
        this.informationsBasDePage = facture.getInformationsBasDePage().stream().map(InformationBasDePageDTO::new).collect(Collectors.toList());
        this.signatures = facture.getSignatures().stream().map(SignatureDTO::new).collect(Collectors.toList());
        this.pdfs = facture.getPdfs().stream().map(PDFStorageDTO::new).collect(Collectors.toList());
        this.draft = facture.isDraft();
        this.pdfFile = facture.getPdfFile();
        this.totalAmount = facture.getTotalAmount();
    }
}
