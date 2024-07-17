package com.test.test.service.impl;

import com.test.test.DTO.ImpotDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Impot;
import com.test.test.mapper.ImpotMapper;
import com.test.test.repository.ImpotRepository;
import com.test.test.service.ImpotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImpotServiceImpl implements ImpotService {

    private final ImpotRepository impotRepository;

    @Override
    public ImpotDTO createImpot(ImpotDTO impotDTO) {
        Impot impot = ImpotMapper.mapToImpot(impotDTO);
        Impot savedImpot = impotRepository.save(impot);
        return ImpotMapper.mapToImpotDTO(savedImpot);
    }

    @Override
    public ImpotDTO getImpotById(Long impotId) {
        Impot impot = impotRepository.findById(impotId)
                .orElseThrow(() -> new ResourceNotFoundException("Impot not found with id: " + impotId));
        return ImpotMapper.mapToImpotDTO(impot);
    }

    @Override
    public List<ImpotDTO> getAllImpots() {
        List<Impot> impots = impotRepository.findAll();
        return impots.stream().map(ImpotMapper::mapToImpotDTO).collect(Collectors.toList());
    }

    @Override
    public ImpotDTO updateImpot(Long impotId, ImpotDTO updatedImpotDTO) {
        Impot existingImpot = impotRepository.findById(impotId)
                .orElseThrow(() -> new ResourceNotFoundException("Impot not found with id: " + impotId));

        existingImpot.setName(updatedImpotDTO.getName());
        existingImpot.setPercentage(updatedImpotDTO.getPercentage());

        Impot updatedImpot = impotRepository.save(existingImpot);
        return ImpotMapper.mapToImpotDTO(updatedImpot);
    }

    @Override
    public void deleteImpot(Long impotId) {
        Impot impot = impotRepository.findById(impotId)
                .orElseThrow(() -> new ResourceNotFoundException("Impot not found with id: " + impotId));
        impotRepository.deleteById(impotId);
    }
}
