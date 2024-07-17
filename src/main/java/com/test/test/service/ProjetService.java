package com.test.test.service;

import com.test.test.DTO.ProjetDTO;

import java.util.List;

public interface ProjetService {
    ProjetDTO createProjet(ProjetDTO projetDTO);
    ProjetDTO getProjetById(Long projetId);
    List<ProjetDTO> getAllProjets();
    ProjetDTO updateProjet(Long projetId, ProjetDTO updatedProjetDTO);
    void deleteProjet(Long projetId);
    long countProjets();
}
