package com.test.test.service;

import com.test.test.DTO.FactureDTO;

import java.util.List;

public interface FactureService {
    FactureDTO createFacture(FactureDTO factureDTO);
    FactureDTO createFactureDraft(FactureDTO factureDTO);
    FactureDTO getFactureById(Long factureId);
    List<FactureDTO> getAllFactures();
    FactureDTO updateFacture(Long factureId, FactureDTO updatedFactureDTO);
    void deleteFacture(Long factureId);
    List<FactureDTO> getFacturesByDraft(boolean draft);
    long countFactures();
    double getTotalAmountFactures();
    List<FactureDTO> getFacturesByUser(String username); // Add this line
}
