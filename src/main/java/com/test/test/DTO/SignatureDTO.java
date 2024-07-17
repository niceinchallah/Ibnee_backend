package com.test.test.DTO;

import com.test.test.entity.Signature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignatureDTO {
    private Long id;
    private String type;
    private byte[] content;

    public SignatureDTO(Signature signature) {
        this.id = signature.getId();
        this.type = signature.getType();
        this.content = signature.getContent();
    }
}
