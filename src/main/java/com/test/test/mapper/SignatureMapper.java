package com.test.test.mapper;

import com.test.test.DTO.SignatureDTO;
import com.test.test.entity.Signature;

public class SignatureMapper {

    public static SignatureDTO mapToSignatureDTO(Signature signature) {
        SignatureDTO signatureDTO = new SignatureDTO();
        signatureDTO.setId(signature.getId());
        signatureDTO.setType(signature.getType());
        signatureDTO.setContent(signature.getContent());
        return signatureDTO;
    }

    public static Signature mapToSignature(SignatureDTO signatureDTO) {
        Signature signature = new Signature();
        signature.setId(signatureDTO.getId());
        signature.setType(signatureDTO.getType());
        signature.setContent(signatureDTO.getContent());
        return signature;
    }
}

