package com.test.test.mapper;

import com.test.test.DTO.PDFStorageDTO;
import com.test.test.entity.PDFStorage;

public class PDFStorageMapper {

    public static PDFStorageDTO mapToPDFStorageDTO(PDFStorage pdfStorage) {
        PDFStorageDTO dto = new PDFStorageDTO();
        dto.setId(pdfStorage.getId());
        dto.setFileName(pdfStorage.getFileName());
        dto.setFileType(pdfStorage.getFileType());
        dto.setData(pdfStorage.getData());
        return dto;
    }

    public static PDFStorage mapToPDFStorage(PDFStorageDTO pdfStorageDTO) {
        PDFStorage entity = new PDFStorage();
        entity.setId(pdfStorageDTO.getId());
        entity.setFileName(pdfStorageDTO.getFileName());
        entity.setFileType(pdfStorageDTO.getFileType());
        entity.setData(pdfStorageDTO.getData());
        return entity;
    }
}
