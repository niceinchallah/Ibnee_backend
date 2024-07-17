package com.test.test.controller;

import com.test.test.DTO.ImpotDTO;
import com.test.test.service.ImpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impots")
@Validated
public class Impotcontroller {

    @Autowired
    private ImpotService impotService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ImpotDTO> createImpot(@RequestBody ImpotDTO impotDTO) {
        ImpotDTO savedImpot = impotService.createImpot(impotDTO);
        return new ResponseEntity<>(savedImpot, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ImpotDTO> getImpotById(@PathVariable("id") Long impotId) {
        ImpotDTO impotDTO = impotService.getImpotById(impotId);
        return ResponseEntity.ok(impotDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ImpotDTO>> getAllImpots() {
        List<ImpotDTO> impotList = impotService.getAllImpots();
        return ResponseEntity.ok(impotList);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ImpotDTO> updateImpot(@PathVariable("id") Long impotId,
                                                @RequestBody ImpotDTO updatedImpot) {
        ImpotDTO impotDTO = impotService.updateImpot(impotId, updatedImpot);
        return ResponseEntity.ok(impotDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteImpot(@PathVariable("id") Long impotId) {
        impotService.deleteImpot(impotId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
