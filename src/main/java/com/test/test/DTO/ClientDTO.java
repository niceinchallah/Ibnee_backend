package com.test.test.DTO;

import com.test.test.validation.ValidPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private long id;
    private String name;
    private String email;
    private String address;
    @ValidPhone
    private String phone;
    private String bank;
    private String indicatif;
    private LocalDate date;

}