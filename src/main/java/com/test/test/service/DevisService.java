package com.test.test.service;

import com.test.test.DTO.DevisDTO;

import java.util.List;

public interface DevisService {
    DevisDTO createDevis(DevisDTO devisDTO);
    DevisDTO createDevisDraft(DevisDTO devisDTO);
    DevisDTO getDevisById(Long devisId);
    List<DevisDTO> getAllDevis();
    List<DevisDTO> getDevisByDraft(boolean draft);
    DevisDTO updateDevis(Long devisId, DevisDTO updatedDevisDTO);
    void deleteDevis(Long devisId);
    long countDevis();
    double getTotalAmountDevis();
    List<DevisDTO> getDevisByUser(String username); // Add this line
}
