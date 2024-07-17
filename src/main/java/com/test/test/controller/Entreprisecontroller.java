package com.test.test.controller;

import com.test.test.DTO.EntrepriseDTO;
import com.test.test.service.Entrepriseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/Entreprises")
@Validated
public class Entreprisecontroller {

    @Autowired
    private Entrepriseservice Entrepriseservice;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EntrepriseDTO> createEntreprise(@RequestBody EntrepriseDTO EntrepriseDTO) {
        EntrepriseDTO savedEntreprise = Entrepriseservice.createEntreprise(EntrepriseDTO);
        return new ResponseEntity<>(savedEntreprise, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/logo")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EntrepriseDTO> uploadLogo(@PathVariable Long id, @RequestParam("logo") MultipartFile logo) throws IOException {
        EntrepriseDTO entreprise = Entrepriseservice.getEntreprisebyid(id);
        entreprise.setLogo(logo.getBytes());
        EntrepriseDTO updatedEntreprise = Entrepriseservice.updateEntreprises(id, entreprise);
        return ResponseEntity.ok(updatedEntreprise);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EntrepriseDTO> getEntrepriseById(@PathVariable("id") Long EntrepriseId) {
        EntrepriseDTO EntrepriseDTO = Entrepriseservice.getEntreprisebyid(EntrepriseId);
        return ResponseEntity.ok(EntrepriseDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<EntrepriseDTO>> getAllEntreprises() {
        List<EntrepriseDTO> Entreprises = Entrepriseservice.getAllEntreprises();
        return ResponseEntity.ok(Entreprises);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EntrepriseDTO> updateEntreprise(@PathVariable("id") Long EntrepriseId,
                                                          @RequestBody EntrepriseDTO updatedEntreprise) {
        EntrepriseDTO EntrepriseDTO = Entrepriseservice.updateEntreprises(EntrepriseId, updatedEntreprise);
        return ResponseEntity.ok(EntrepriseDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteEntreprise(@PathVariable("id") Long EntrepriseId) {
        Entrepriseservice.deleteEntreprises(EntrepriseId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
