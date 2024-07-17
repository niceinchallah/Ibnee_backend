package com.test.test.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class materialDTO {
    private Long id;
    private String name;
    private String price;
    private LocalDate date;
}
