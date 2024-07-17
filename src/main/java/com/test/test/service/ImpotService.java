package com.test.test.service;

import com.test.test.DTO.ImpotDTO;

import java.util.List;

public interface ImpotService {
    ImpotDTO createImpot(ImpotDTO impotDTO);
    ImpotDTO getImpotById(Long impotId);
    List<ImpotDTO> getAllImpots();
    ImpotDTO updateImpot(Long impotId, ImpotDTO updatedImpotDTO);
    void deleteImpot(Long impotId);
}
