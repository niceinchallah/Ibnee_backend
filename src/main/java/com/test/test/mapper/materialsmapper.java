package com.test.test.mapper;
import com.test.test.DTO.materialDTO;
import com.test.test.entity.materialsent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class materialsmapper {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static materialDTO mapTomaterialDTO(materialsent materialsent){
        return new materialDTO(
                materialsent.getId(),
                materialsent.getName(),
                materialsent.getPrice(),
                materialsent.getDate()
        );
    }
    public static materialsent mapTomaterialsent(materialDTO materialDTO){
        return new materialsent(
                materialDTO.getId(),
                materialDTO.getName(),
                materialDTO.getPrice(),
                materialDTO.getDate()
        );
    }
    private static String formatLocalDateToString(LocalDate date) {
        return date != null ? date.format(dateFormatter) : null;
    }

    private static LocalDate parseStringToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, dateFormatter) : null;
    }
}
