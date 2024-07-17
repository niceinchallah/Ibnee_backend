package com.test.test.controller;

import com.test.test.DTO.FactureDTO;
import com.test.test.service.FactureService;
import com.test.test.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public ResponseEntity<FactureDTO> createFacture(
            @RequestPart("facture") FactureDTO factureDTO,
            @RequestPart("pdfFile") MultipartFile pdfFile) throws IOException {

        factureDTO.setPdfFile(pdfFile.getBytes());
        FactureDTO savedFacture = factureService.createFacture(factureDTO);
        return new ResponseEntity<>(savedFacture, HttpStatus.CREATED);
    }

    @PostMapping("/draft")
    public ResponseEntity<FactureDTO> saveDraft(@RequestBody FactureDTO factureDTO) {
        FactureDTO draftFacture = factureService.createFactureDraft(factureDTO);
        return ResponseEntity.ok(draftFacture);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFactureById(@PathVariable("id") Long factureId) {
        FactureDTO factureDTO = factureService.getFactureById(factureId);
        return ResponseEntity.ok(factureDTO);
    }

    @GetMapping
    public ResponseEntity<List<FactureDTO>> getFactures(@RequestParam(value = "draft", required = false) Boolean draft) {
        List<FactureDTO> factureList;
        if (draft != null) {
            factureList = factureService.getFacturesByDraft(draft);
        } else {
            factureList = factureService.getAllFactures();
        }
        return ResponseEntity.ok(factureList);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countFactures() {
        long count = factureService.countFactures();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<Double> getTotalAmountFactures() {
        double totalAmount = factureService.getTotalAmountFactures();
        return ResponseEntity.ok(totalAmount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDTO> updateFacture(@PathVariable("id") Long factureId,
                                                    @RequestBody FactureDTO updatedFacture) {
        FactureDTO factureDTO = factureService.updateFacture(factureId, updatedFacture);
        return ResponseEntity.ok(factureDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacture(@PathVariable("id") Long factureId) {
        factureService.deleteFacture(factureId);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/user")
    public ResponseEntity<List<FactureDTO>> getFacturesForUser() {
        String username = userDetailsService.getCurrentUser().getUsername();
        List<FactureDTO> factures = factureService.getFacturesByUser(username);
        return ResponseEntity.ok(factures);
    }
}
