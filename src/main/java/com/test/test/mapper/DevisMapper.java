package com.test.test.mapper;

import com.test.test.DTO.DevisDTO;
import com.test.test.entity.Client;
import com.test.test.entity.Devis;
import com.test.test.entity.Entreprise;

import java.util.stream.Collectors;

public class DevisMapper {

    public static DevisDTO mapToDevisDTO(Devis devis) {
        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setId(devis.getId());
        devisDTO.setNumeroDeDevis(devis.getNumeroDeDevis());
        devisDTO.setDateDeDevis(devis.getDateDeDevis());
        devisDTO.setClientId(devis.getClient().getId());
        devisDTO.setEntrepriseId(devis.getEntreprise().getId());
        devisDTO.setStatus(devis.getStatus());
        devisDTO.setImpots(devis.getImpots().stream().map(ImpotMapper::mapToImpotDTO).collect(Collectors.toList()));
        devisDTO.setArticles(devis.getArticles().stream().map(ArticleMapper::mapToArticleDTO).collect(Collectors.toList()));
        devisDTO.setInformationsBasDePage(devis.getInformationsBasDePage().stream().map(InformationBasDePagemapper::mapToInformationBasDePageDTO).collect(Collectors.toList()));
        devisDTO.setSignatures(devis.getSignatures().stream().map(SignatureMapper::mapToSignatureDTO).collect(Collectors.toList()));
        devisDTO.setPdfs(devis.getPdfs().stream().map(PDFStorageMapper::mapToPDFStorageDTO).collect(Collectors.toList()));
        devisDTO.setTotalAmount(devis.getTotalAmount());
        return devisDTO;
    }

    public static Devis mapToDevis(DevisDTO devisDTO) {
        Devis devis = new Devis();
        devis.setId(devisDTO.getId());
        devis.setNumeroDeDevis(devisDTO.getNumeroDeDevis());
        devis.setDateDeDevis(devisDTO.getDateDeDevis());
        devis.setClient(new Client(devisDTO.getClientId()));
        devis.setEntreprise(new Entreprise(devisDTO.getEntrepriseId()));
        devis.setStatus(devisDTO.getStatus());
        devis.setImpots(devisDTO.getImpots().stream().map(ImpotMapper::mapToImpot).collect(Collectors.toList()));
        devis.setArticles(devisDTO.getArticles().stream().map(ArticleMapper::mapToArticle).collect(Collectors.toList()));
        devis.setInformationsBasDePage(devisDTO.getInformationsBasDePage().stream().map(InformationBasDePagemapper::mapToInformationBasDePage).collect(Collectors.toList()));
        devis.setSignatures(devisDTO.getSignatures().stream().map(SignatureMapper::mapToSignature).collect(Collectors.toList()));
        devis.setPdfs(devisDTO.getPdfs().stream().map(PDFStorageMapper::mapToPDFStorage).collect(Collectors.toList()));
        return devis;
    }
}
