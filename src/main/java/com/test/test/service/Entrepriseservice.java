package com.test.test.service;

import com.test.test.DTO.EntrepriseDTO;

import java.util.List;

public interface Entrepriseservice {
    EntrepriseDTO createEntreprise(EntrepriseDTO EntrepriseDTO);
    EntrepriseDTO getEntreprisebyid(Long EntrepriseID);

    List<EntrepriseDTO> getAllEntreprises();
    EntrepriseDTO updateEntreprises(Long Entrepriseid,EntrepriseDTO updatedEntreprises);
    void  deleteEntreprises(Long Entrepriseid);
}
