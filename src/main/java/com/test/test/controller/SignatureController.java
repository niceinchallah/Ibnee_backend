package com.test.test.controller;

import com.test.test.DTO.SignatureDTO;
import com.test.test.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signatures")
@Validated
public class SignatureController {

    @Autowired
    private SignatureService signatureService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<SignatureDTO> createSignature(@RequestBody SignatureDTO signatureDTO) {
        SignatureDTO savedSignature = signatureService.createSignature(signatureDTO);
        return new ResponseEntity<>(savedSignature, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<SignatureDTO> getSignatureById(@PathVariable("id") Long signatureId) {
        SignatureDTO signatureDTO = signatureService.getSignatureById(signatureId);
        return ResponseEntity.ok(signatureDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<SignatureDTO>> getAllSignatures() {
        List<SignatureDTO> signatureList = signatureService.getAllSignatures();
        return ResponseEntity.ok(signatureList);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<SignatureDTO> updateSignature(@PathVariable("id") Long signatureId,
                                                        @RequestBody SignatureDTO updatedSignature) {
        SignatureDTO signatureDTO = signatureService.updateSignature(signatureId, updatedSignature);
        return ResponseEntity.ok(signatureDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteSignature(@PathVariable("id") Long signatureId) {
        signatureService.deleteSignature(signatureId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
