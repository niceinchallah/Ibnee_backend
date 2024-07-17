package com.test.test.DTO;

import com.test.test.validation.ValidPhone;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDTO {

    private long id;
    private String name;
    private String email;
    private String adress;
    @ValidPhone
    private String phone;
    private String bank;
    private LocalDate date;
    private byte[] logo;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }
}
