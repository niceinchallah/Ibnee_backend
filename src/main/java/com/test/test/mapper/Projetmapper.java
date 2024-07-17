package com.test.test.mapper;

import com.test.test.DTO.ProjetDTO;
import com.test.test.entity.Projet;

public class Projetmapper  {

    public static ProjetDTO toDTO(Projet projet) {
        ProjetDTO projetDTO = new ProjetDTO();
        projetDTO.setId(projet.getId());
        projetDTO.setName(projet.getName());
        projetDTO.setDescription(projet.getDescription());
        projetDTO.setDebut(projet.getDebut());
        projetDTO.setFin(projet.getFin());
        projetDTO.setServicePrincipal(projet.getServicePrincipal());
        projetDTO.setClient(projet.getClient());

        return projetDTO;
    }

    public static Projet toEntity(ProjetDTO projetDTO) {
        Projet projet = new Projet();
        projet.setId(projetDTO.getId());
        projet.setName(projetDTO.getName());
        projet.setDescription(projetDTO.getDescription());
        projet.setDebut(projetDTO.getDebut());
        projet.setFin(projetDTO.getFin());
        projet.setServicePrincipal(projetDTO.getServicePrincipal());
        projet.setClient(projetDTO.getClient());

        return projet;
    }
}
