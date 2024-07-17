package com.test.test.mapper;
import com.test.test.entity.Employeent;
import lombok.Getter;
import lombok.Setter;
import com.test.test.DTO.EmployeDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Getter
@Setter
public class Employemapper {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static EmployeDTO mapToEmployeDTO(Employeent Employeent){
        return new EmployeDTO(
                Employeent.getId(),
                Employeent.getName(),
                Employeent.getSalary(),
                Employeent.getDate()// Convertir java.time.LocalDate en String
        );
    }


    public static Employeent mapToEmployeent(EmployeDTO EmployeDTO){
        return new Employeent(
                EmployeDTO.getId(),
                EmployeDTO.getName(),
                EmployeDTO.getSalary(),
                EmployeDTO.getDate() // Convertir String en java.time.LocalDate
        );
    }

    private static String formatLocalDateToString(LocalDate date) {
        return date != null ? date.format(dateFormatter) : null;
    }

    private static LocalDate parseStringToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, dateFormatter) : null;
    }
}



