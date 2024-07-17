package com.test.test.controller;

import com.test.test.DTO.DevisDTO;
import com.test.test.service.DevisService;
import com.test.test.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/devis")
@CrossOrigin(origins = "http://localhost:3000")
public class DevisController {

    private final DevisService devisService;
    private final UserDetailsServiceImpl userDetailsService;

    public DevisController(DevisService devisService, UserDetailsServiceImpl userDetailsService) {
        this.devisService = devisService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/create")
    public ResponseEntity<DevisDTO> createDevis(
            @RequestPart("devis") DevisDTO devisDTO,
            @RequestPart("pdfFile") MultipartFile pdfFile) throws IOException {

        devisDTO.setPdfFile(pdfFile.getBytes());
        DevisDTO savedDevis = devisService.createDevis(devisDTO);
        return ResponseEntity.ok(savedDevis);
    }

    @PostMapping("/draft")
    public ResponseEntity<DevisDTO> saveDraft(@RequestBody DevisDTO devisDTO) {
        DevisDTO draftDevis = devisService.createDevisDraft(devisDTO);
        return ResponseEntity.ok(draftDevis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisDTO> getDevisById(@PathVariable Long id) {
        DevisDTO devisDTO = devisService.getDevisById(id);
        return ResponseEntity.ok(devisDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DevisDTO>> getAllDevis() {
        List<DevisDTO> devisList = devisService.getAllDevis();
        return ResponseEntity.ok(devisList);
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<DevisDTO>> getDevisByDraft(@RequestParam("draft") boolean draft) {
        List<DevisDTO> devisList = devisService.getDevisByDraft(draft);
        return ResponseEntity.ok(devisList);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countDevis() {
        long count = devisService.countDevis();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<Double> getTotalAmountDevis() {
        double totalAmount = devisService.getTotalAmountDevis();
        return ResponseEntity.ok(totalAmount);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DevisDTO> updateDevis(@PathVariable Long id, @RequestBody DevisDTO devisDTO) {
        DevisDTO updatedDevis = devisService.updateDevis(id, devisDTO);
        return ResponseEntity.ok(updatedDevis);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        devisService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<DevisDTO>> getDevisForUser() {
        String username = userDetailsService.getCurrentUser().getUsername();
        List<DevisDTO> devis = devisService.getDevisByUser(username);
        return ResponseEntity.ok(devis);
    }
}
