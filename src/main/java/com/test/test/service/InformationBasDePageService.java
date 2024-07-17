package com.test.test.service;

import com.test.test.DTO.InformationBasDePageDTO;

import java.util.List;

public interface InformationBasDePageService {
    InformationBasDePageDTO createInformationBasDePage(InformationBasDePageDTO informationBasDePageDTO);
    InformationBasDePageDTO getInformationBasDePageById(Long informationBasDePageId);
    List<InformationBasDePageDTO> getAllInformationBasDePage();
    InformationBasDePageDTO updateInformationBasDePage(Long informationBasDePageId, InformationBasDePageDTO updatedInformationBasDePageDTO);
    void deleteInformationBasDePage(Long informationBasDePageId);
}
