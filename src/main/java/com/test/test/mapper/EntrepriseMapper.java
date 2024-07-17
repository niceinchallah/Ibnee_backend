package com.test.test.mapper;

import com.test.test.DTO.EntrepriseDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.test.test.entity.Entreprise;
@Getter
@Setter
public class EntrepriseMapper {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static EntrepriseDTO mapToEntrepriseDTO(Entreprise Entreprise ){
        return new EntrepriseDTO(
                Entreprise.getId(),
                Entreprise.getName(),
                Entreprise.getEmail(),
                Entreprise.getAdress(),
                Entreprise.getPhone(),
                Entreprise.getBank(),
                Entreprise.getDate(),
                Entreprise.getLogo()
        );
    }
    public static Entreprise mapToEntreprise(EntrepriseDTO EntrepriseDTO){
        return new Entreprise(
                EntrepriseDTO.getId(),
                EntrepriseDTO.getName(),
                EntrepriseDTO.getEmail(),
                EntrepriseDTO.getAdress(),
                EntrepriseDTO.getPhone(),
                EntrepriseDTO.getBank(),
                EntrepriseDTO.getDate(),
                EntrepriseDTO.getLogo()
        );
    }
    private static String formatLocalDateToString(LocalDate date) {
        return date != null ? date.format(dateFormatter) : null;
    }

    private static LocalDate parseStringToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, dateFormatter) : null;
    }
}