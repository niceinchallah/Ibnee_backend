package com.test.test.DTO;

import com.test.test.entity.Devis;
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
public class DevisDTO {
        private Long id;
        private String numeroDeDevis;
        private LocalDate dateDeDevis;
        private Long clientId;
        private Long entrepriseId;
        private String status;
        private List<ArticleDTO> articles;
        private List<ImpotDTO> impots;
        private List<InformationBasDePageDTO> informationsBasDePage;
        private List<SignatureDTO> signatures;
        private List<PDFStorageDTO> pdfs;
        private boolean draft;
        private byte[] pdfFile;
        private double totalAmount;

        public DevisDTO(Devis devis) {
                this.id = devis.getId();
                this.numeroDeDevis = devis.getNumeroDeDevis();
                this.dateDeDevis = devis.getDateDeDevis();
                this.clientId = devis.getClient().getId();
                this.entrepriseId = devis.getEntreprise().getId();
                this.status = devis.getStatus();
                this.articles = devis.getArticles().stream().map(ArticleDTO::new).collect(Collectors.toList());
                this.impots = devis.getImpots().stream().map(ImpotDTO::new).collect(Collectors.toList());
                this.informationsBasDePage = devis.getInformationsBasDePage().stream().map(InformationBasDePageDTO::new).collect(Collectors.toList());
                this.signatures = devis.getSignatures().stream().map(SignatureDTO::new).collect(Collectors.toList());
                this.pdfs = devis.getPdfs().stream().map(PDFStorageDTO::new).collect(Collectors.toList());
                this.draft = devis.isDraft();
                this.pdfFile = devis.getPdfFile();
                this.totalAmount = devis.getTotalAmount();
        }
}
