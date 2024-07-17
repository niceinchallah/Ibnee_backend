package com.test.test.service.impl;

import com.test.test.DTO.InformationBasDePageDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.InformationBasDePage;
import com.test.test.mapper.InformationBasDePagemapper;
import com.test.test.repository.InformationBasDePagerep;
import com.test.test.service.InformationBasDePageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InformationBasDePageServiceImpl implements InformationBasDePageService {

    private final InformationBasDePagerep informationBasDePagerep;

    @Override
    public InformationBasDePageDTO createInformationBasDePage(InformationBasDePageDTO informationBasDePageDTO) {
        InformationBasDePage informationBasDePage = InformationBasDePagemapper.mapToInformationBasDePage(informationBasDePageDTO);
        InformationBasDePage savedInformationBasDePage = informationBasDePagerep.save(informationBasDePage);
        return InformationBasDePagemapper.mapToInformationBasDePageDTO(savedInformationBasDePage);
    }

    @Override
    public InformationBasDePageDTO getInformationBasDePageById(Long informationBasDePageId) {
        InformationBasDePage informationBasDePage = informationBasDePagerep.findById(informationBasDePageId)
                .orElseThrow(() -> new ResourceNotFoundException("InformationBasDePage not found with id: " + informationBasDePageId));
        return InformationBasDePagemapper.mapToInformationBasDePageDTO(informationBasDePage);
    }

    @Override
    public List<InformationBasDePageDTO> getAllInformationBasDePage() {
        List<InformationBasDePage> informationBasDePageList = informationBasDePagerep.findAll();
        return informationBasDePageList.stream().map(InformationBasDePagemapper::mapToInformationBasDePageDTO).collect(Collectors.toList());
    }

    @Override
    public InformationBasDePageDTO updateInformationBasDePage(Long informationBasDePageId, InformationBasDePageDTO updatedInformationBasDePageDTO) {
        InformationBasDePage existingInformationBasDePage = informationBasDePagerep.findById(informationBasDePageId)
                .orElseThrow(() -> new ResourceNotFoundException("InformationBasDePage not found with id: " + informationBasDePageId));

        existingInformationBasDePage.setCompanyDetails(updatedInformationBasDePageDTO.getCompanyDetails());
        existingInformationBasDePage.setContactDetails(updatedInformationBasDePageDTO.getContactDetails());
        existingInformationBasDePage.setBankDetails(updatedInformationBasDePageDTO.getBankDetails());

        InformationBasDePage updatedInformationBasDePage = informationBasDePagerep.save(existingInformationBasDePage);
        return InformationBasDePagemapper.mapToInformationBasDePageDTO(updatedInformationBasDePage);
    }

    @Override
    public void deleteInformationBasDePage(Long informationBasDePageId) {
        InformationBasDePage informationBasDePage = informationBasDePagerep.findById(informationBasDePageId)
                .orElseThrow(() -> new ResourceNotFoundException("InformationBasDePage not found with id: " + informationBasDePageId));
        informationBasDePagerep.deleteById(informationBasDePageId);
    }
}
