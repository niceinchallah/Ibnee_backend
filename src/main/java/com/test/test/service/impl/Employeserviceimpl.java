package com.test.test.service.impl;

import com.test.test.DTO.EmployeDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Employeent;
import com.test.test.mapper.Employemapper;
import com.test.test.repository.Employerep;
import com.test.test.service.Employeservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class Employeserviceimpl implements Employeservice {

    private Employerep employerep;
    @Override
    public EmployeDTO createEmploye(EmployeDTO employeDTO) {
        Employeent employeent= Employemapper.mapToEmployeent(employeDTO);
        employeent.setDate(LocalDate.now());
        Employeent savedEmploye=employerep.save(employeent);
        return Employemapper.mapToEmployeDTO(savedEmploye);
    }

    @Override
    public EmployeDTO getEmployeid(Long Employeid) {
        Employeent employeent = employerep.findById(Employeid)
                .orElseThrow(()->
                        new ResourceNotFoundException ("not exist with given id " + Employeid));
        return Employemapper.mapToEmployeDTO(employeent);
    }

    @Override
    public List<EmployeDTO> getAllEmployees() {
        return null;
    }

    @Override
    public EmployeDTO updateEmployees(Long Employeid, EmployeDTO updatedEmployees) {
        Employeent employeent= employerep.findById(Employeid).orElseThrow(()
        -> new ResourceNotFoundException("not exist with given id "+ Employeid)
                );
        employeent.setName(updatedEmployees.getName());
        employeent.setId(updatedEmployees.getId());
        employeent.setDate(updatedEmployees.getDate());
        employeent.setSalary(updatedEmployees.getSalary());
        Employeent updatedEmployeeObj = employerep.save(employeent);

        return Employemapper.mapToEmployeDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployees(Long Employeid) {
        Employeent employeent= employerep.findById(Employeid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with given id "+ Employeid)
        );
        employerep.deleteById(Employeid);
    }
}
