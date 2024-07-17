package com.test.test.service.impl;

import com.test.test.DTO.SignatureDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Signature;
import com.test.test.mapper.SignatureMapper;
import com.test.test.repository.SignatureRepository;
import com.test.test.service.SignatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SignatureServiceImpl implements SignatureService {

    private final SignatureRepository signatureRepository;

    @Override
    public SignatureDTO createSignature(SignatureDTO signatureDTO) {
        Signature signature = SignatureMapper.mapToSignature(signatureDTO);
        Signature savedSignature = signatureRepository.save(signature);
        return SignatureMapper.mapToSignatureDTO(savedSignature);
    }

    @Override
    public SignatureDTO getSignatureById(Long signatureId) {
        Signature signature = signatureRepository.findById(signatureId)
                .orElseThrow(() -> new ResourceNotFoundException("Signature not found with id: " + signatureId));
        return SignatureMapper.mapToSignatureDTO(signature);
    }

    @Override
    public List<SignatureDTO> getAllSignatures() {
        List<Signature> signatures = signatureRepository.findAll();
        return signatures.stream().map(SignatureMapper::mapToSignatureDTO).collect(Collectors.toList());
    }

    @Override
    public SignatureDTO updateSignature(Long signatureId, SignatureDTO updatedSignatureDTO) {
        Signature existingSignature = signatureRepository.findById(signatureId)
                .orElseThrow(() -> new ResourceNotFoundException("Signature not found with id: " + signatureId));

        existingSignature.setType(updatedSignatureDTO.getType());
        existingSignature.setContent(updatedSignatureDTO.getContent());

        Signature updatedSignature = signatureRepository.save(existingSignature);
        return SignatureMapper.mapToSignatureDTO(updatedSignature);
    }

    @Override
    public void deleteSignature(Long signatureId) {
        Signature signature = signatureRepository.findById(signatureId)
                .orElseThrow(() -> new ResourceNotFoundException("Signature not found with id: " + signatureId));
        signatureRepository.deleteById(signatureId);
    }
}
