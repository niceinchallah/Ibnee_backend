package com.test.test.DTO;


import com.test.test.entity.Employeent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {
    private Long id;
    private String name;
    private String salary;
    private LocalDate date; // Update type to String

    // Constructor with String argument for date
    /*public EmployeDTO(Long id, String name, String salary, String date) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.date = date;
    }*/
}

