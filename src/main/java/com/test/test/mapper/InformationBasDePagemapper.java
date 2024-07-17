package com.test.test.mapper;

import com.test.test.DTO.InformationBasDePageDTO;
import com.test.test.entity.InformationBasDePage;

public class InformationBasDePagemapper {

    public static InformationBasDePageDTO mapToInformationBasDePageDTO(InformationBasDePage informationBasDePage) {
        InformationBasDePageDTO dto = new InformationBasDePageDTO();
        dto.setId(informationBasDePage.getId());
        dto.setAdditionnel(informationBasDePage.getAdditionnel());
        dto.setCompanyDetails(informationBasDePage.getCompanyDetails());
        dto.setContactDetails(informationBasDePage.getContactDetails());
        dto.setBankDetails(informationBasDePage.getBankDetails());


        return dto;
    }

    public static InformationBasDePage mapToInformationBasDePage(InformationBasDePageDTO informationBasDePageDTO) {
        InformationBasDePage entity = new InformationBasDePage();
        entity.setId(informationBasDePageDTO.getId());
        entity.setAdditionnel(informationBasDePageDTO.getAdditionnel());
        entity.setCompanyDetails(informationBasDePageDTO.getCompanyDetails());
        entity.setContactDetails(informationBasDePageDTO.getContactDetails());
        entity.setBankDetails(informationBasDePageDTO.getBankDetails());
        return entity;
    }
}
