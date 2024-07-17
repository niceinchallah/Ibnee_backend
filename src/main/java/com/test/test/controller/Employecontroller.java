package com.test.test.controller;

import com.test.test.DTO.EmployeDTO;
import com.test.test.service.Employeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employe")
public class Employecontroller {

    @Autowired
    private Employeservice employeService;
    //build add request rest API
    @PostMapping
    public ResponseEntity<EmployeDTO>createEmploye(@RequestBody  EmployeDTO employeDTO){
        EmployeDTO savedEmployees = employeService.createEmploye(employeDTO);
        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
    }
    //build get **rest api
    @GetMapping("id")
    public ResponseEntity<EmployeDTO>getEmployebyid(@RequestParam("id") Long Employeid){
        EmployeDTO employeDTO = employeService.getEmployeid(Employeid);
        return ResponseEntity.ok(employeDTO);
    }
    //Build get alll rest api
    @GetMapping
    public  ResponseEntity<List<EmployeDTO>> getAllEmployess(){
        List<EmployeDTO> employees= employeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
// Build update rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeDTO> updateEmployees(@PathVariable("id") Long Employeesid,@RequestBody EmployeDTO updatedEmployees){
        EmployeDTO employeDTO = employeService.updateEmployees(Employeesid, updatedEmployees);
        return ResponseEntity.ok(employeDTO);
    }
    //BUILD DELETE REST API
    public ResponseEntity<String> deleteEmployees(Long Employeid){
        employeService.deleteEmployees(Employeid);
        return ResponseEntity.ok("deleted succefuly!");
    }

}
