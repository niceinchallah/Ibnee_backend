package com.test.test.mapper;

import com.test.test.DTO.FactureDTO;
import com.test.test.entity.Client;
import com.test.test.entity.Entreprise;
import com.test.test.entity.Facture;

import java.util.stream.Collectors;

public class FactureMapper {

    public static FactureDTO mapToFactureDTO(Facture facture) {
        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setId(facture.getId());
        factureDTO.setNumeroDeFacture(facture.getNumeroDeFacture());
        factureDTO.setDateDeFacture(facture.getDateDeFacture());
        factureDTO.setDateEcheance(facture.getDateDEcheance());
        factureDTO.setClientId(facture.getClient().getId());
        factureDTO.setEntrepriseId(facture.getEntreprise().getId());
        factureDTO.setImpots(facture.getImpots().stream().map(ImpotMapper::mapToImpotDTO).collect(Collectors.toList()));
        factureDTO.setArticles(facture.getArticles().stream().map(ArticleMapper::mapToArticleDTO).collect(Collectors.toList()));
        factureDTO.setInformationsBasDePage(facture.getInformationsBasDePage().stream().map(InformationBasDePagemapper::mapToInformationBasDePageDTO).collect(Collectors.toList()));
        factureDTO.setSignatures(facture.getSignatures().stream().map(SignatureMapper::mapToSignatureDTO).collect(Collectors.toList()));
        factureDTO.setTotalAmount(facture.getTotalAmount());
        return factureDTO;
    }

    public static Facture mapToFacture(FactureDTO factureDTO) {
        Facture facture = new Facture();
        facture.setId(factureDTO.getId());
        facture.setNumeroDeFacture(factureDTO.getNumeroDeFacture());
        facture.setDateDeFacture(factureDTO.getDateDeFacture());
        facture.setDateDEcheance(factureDTO.getDateEcheance());
        facture.setClient(new Client(factureDTO.getClientId()));
        facture.setEntreprise(new Entreprise(factureDTO.getEntrepriseId()));
        facture.setImpots(factureDTO.getImpots().stream().map(ImpotMapper::mapToImpot).collect(Collectors.toList()));
        facture.setArticles(factureDTO.getArticles().stream().map(ArticleMapper::mapToArticle).collect(Collectors.toList()));
        facture.setInformationsBasDePage(factureDTO.getInformationsBasDePage().stream().map(InformationBasDePagemapper::mapToInformationBasDePage).collect(Collectors.toList()));
        facture.setSignatures(factureDTO.getSignatures().stream().map(SignatureMapper::mapToSignature).collect(Collectors.toList()));
        return facture;
    }
}
