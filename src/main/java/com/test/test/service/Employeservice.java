package com.test.test.service;

import com.test.test.DTO.EmployeDTO;

import java.util.List;

public interface Employeservice {
    EmployeDTO createEmploye(EmployeDTO employeDTO);
    EmployeDTO getEmployeid(Long Employeid);
    List<EmployeDTO> getAllEmployees();
    EmployeDTO updateEmployees(Long Employeid,EmployeDTO updatedEmployees);
    void deleteEmployees(Long Employeid);
}
