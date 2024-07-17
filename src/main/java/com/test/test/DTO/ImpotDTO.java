package com.test.test.DTO;

import com.test.test.entity.Impot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpotDTO {
    private Long id;
    private String name;
    private double percentage;

    public ImpotDTO(Impot impot) {
        this.id = impot.getId();
        this.name = impot.getName();
        this.percentage = impot.getPercentage();
    }
}
