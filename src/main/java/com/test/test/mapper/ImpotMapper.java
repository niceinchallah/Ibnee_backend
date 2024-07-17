package com.test.test.mapper;

import com.test.test.DTO.ImpotDTO;
import com.test.test.entity.Impot;

public class ImpotMapper {

    public static ImpotDTO mapToImpotDTO(Impot impot) {
        ImpotDTO impotDTO = new ImpotDTO();
        impotDTO.setId(impot.getId());
        impotDTO.setName(impot.getName());
        impotDTO.setPercentage(impot.getPercentage());
        return impotDTO;
    }

    public static Impot mapToImpot(ImpotDTO impotDTO) {
        Impot impot = new Impot();
        impot.setId(impotDTO.getId());
        impot.setName(impotDTO.getName());
        impot.setPercentage(impotDTO.getPercentage());
        return impot;
    }
}
