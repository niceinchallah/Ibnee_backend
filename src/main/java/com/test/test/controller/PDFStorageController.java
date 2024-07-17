package com.test.test.controller;

import com.test.test.DTO.PDFStorageDTO;
import com.test.test.service.PDFStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdf-storage")
@Validated
public class PDFStorageController {

    @Autowired
    private PDFStorageService pdfStorageService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<PDFStorageDTO> savePDF(@RequestBody PDFStorageDTO pdfStorageDTO) {
        PDFStorageDTO savedPDFStorage = pdfStorageService.savePDF(pdfStorageDTO);
        return new ResponseEntity<>(savedPDFStorage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<PDFStorageDTO> getPDFById(@PathVariable("id") Long pdfId) {
        PDFStorageDTO pdfStorageDTO = pdfStorageService.getPDFById(pdfId);
        return ResponseEntity.ok(pdfStorageDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<PDFStorageDTO>> getAllPDFs() {
        List<PDFStorageDTO> pdfStorageList = pdfStorageService.getAllPDFs();
        return ResponseEntity.ok(pdfStorageList);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deletePDF(@PathVariable("id") Long pdfId) {
        pdfStorageService.deletePDF(pdfId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
