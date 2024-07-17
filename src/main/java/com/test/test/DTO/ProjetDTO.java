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
public class ProjetDTO {
    private long id;
    private String name;
    private String description;
    private LocalDate debut;
    private LocalDate fin;
    private String servicePrincipal;
    private String client;


    // Getters and Setters
    // ...
}
