package com.test.test.service.impl;

import com.test.test.DTO.PDFStorageDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.PDFStorage;
import com.test.test.mapper.PDFStorageMapper;
import com.test.test.repository.PDFStorageRepository;
import com.test.test.service.PDFStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PDFStorageServiceImpl implements PDFStorageService {

    private final PDFStorageRepository pdfStorageRepository;

    @Override
    public PDFStorageDTO savePDF(PDFStorageDTO pdfStorageDTO) {
        PDFStorage pdfStorage = PDFStorageMapper.mapToPDFStorage(pdfStorageDTO);
        PDFStorage savedPDFStorage = pdfStorageRepository.save(pdfStorage);
        return PDFStorageMapper.mapToPDFStorageDTO(savedPDFStorage);
    }

    @Override
    public PDFStorageDTO getPDFById(Long pdfId) {
        PDFStorage pdfStorage = pdfStorageRepository.findById(pdfId)
                .orElseThrow(() -> new ResourceNotFoundException("PDF not found with id: " + pdfId));
        return PDFStorageMapper.mapToPDFStorageDTO(pdfStorage);
    }

    @Override
    public List<PDFStorageDTO> getAllPDFs() {
        List<PDFStorage> pdfStorageList = pdfStorageRepository.findAll();
        return pdfStorageList.stream().map(PDFStorageMapper::mapToPDFStorageDTO).collect(Collectors.toList());
    }

    @Override
    public void deletePDF(Long pdfId) {
        PDFStorage pdfStorage = pdfStorageRepository.findById(pdfId)
                .orElseThrow(() -> new ResourceNotFoundException("PDF not found with id: " + pdfId));
        pdfStorageRepository.deleteById(pdfId);
    }
}
