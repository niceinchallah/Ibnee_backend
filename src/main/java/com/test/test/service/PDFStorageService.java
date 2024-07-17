package com.test.test.service;

import com.test.test.DTO.PDFStorageDTO;

import java.util.List;

public interface PDFStorageService {
    PDFStorageDTO savePDF(PDFStorageDTO pdfStorageDTO);
    PDFStorageDTO getPDFById(Long pdfId);
    List<PDFStorageDTO> getAllPDFs();
    void deletePDF(Long pdfId);
}
