package com.test.test.controller;

import com.test.test.DTO.ProjetDTO;
import com.test.test.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@Validated
public class Projetcontroller {

    @Autowired
    private ProjetService projetService;

    @PostMapping
    public ResponseEntity<ProjetDTO> createProjet(@RequestBody ProjetDTO projetDTO) {
        ProjetDTO savedProjet = projetService.createProjet(projetDTO);
        return new ResponseEntity<>(savedProjet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetById(@PathVariable("id") Long projetId) {
        ProjetDTO projetDTO = projetService.getProjetById(projetId);
        return ResponseEntity.ok(projetDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjetDTO>> getAllProjets() {
        List<ProjetDTO> projetList = projetService.getAllProjets();
        return ResponseEntity.ok(projetList);
    }

    @GetMapping("/count")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Long> countProjets() {
        long count = projetService.countProjets();
        return ResponseEntity.ok(count);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjetDTO> updateProjet(@PathVariable("id") Long projetId,
                                                  @RequestBody ProjetDTO updatedProjet) {
        ProjetDTO projetDTO = projetService.updateProjet(projetId, updatedProjet);
        return ResponseEntity.ok(projetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable("id") Long projetId) {
        projetService.deleteProjet(projetId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
