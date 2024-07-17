package com.test.test.service;

import com.test.test.DTO.SignatureDTO;

import java.util.List;

public interface SignatureService {
    SignatureDTO createSignature(SignatureDTO signatureDTO);
    SignatureDTO getSignatureById(Long signatureId);
    List<SignatureDTO> getAllSignatures();
    SignatureDTO updateSignature(Long signatureId, SignatureDTO updatedSignatureDTO);
    void deleteSignature(Long signatureId);
}
