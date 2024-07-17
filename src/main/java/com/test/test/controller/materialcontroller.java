package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.test.service.materialservice;
import com.test.test.DTO.materialDTO;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class materialcontroller {
    @Autowired
    private materialservice materialservice;
    //build add request api
   @PostMapping
    public ResponseEntity<materialDTO> createClient(@RequestBody materialDTO materialDTO){
        materialDTO savedmaterials = materialservice.creatematerial(materialDTO);
        return new ResponseEntity<>(savedmaterials, HttpStatus.CREATED);
    }
    //build get **rest api
    @GetMapping("id")
    public ResponseEntity<materialDTO>getmaterialbyid(@RequestParam("id") Long materialid){
       materialDTO materialDTO = materialservice.getmaterialbyid(materialid);
       return ResponseEntity.ok(materialDTO);
    }
//Build get alll rest api
    @GetMapping
    public  ResponseEntity<List<materialDTO>> getAllmaterials(){
       List<materialDTO> materials = materialservice.getAllmaterials();
       return ResponseEntity.ok(materials);
    }
//Build update rest api
    @PutMapping("{id}")
    public ResponseEntity<materialDTO>updatematerials(@PathVariable("id")Long materialid,
                                                      @RequestBody materialDTO updatedmaterials){
       materialDTO materialDTO = materialservice.updatematerials(materialid, updatedmaterials);
       return ResponseEntity.ok(materialDTO);
    }
    //BUILD DELETE REST API

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletematerial(@PathVariable("id") Long id){
        materialservice.deletematerial(id);
        return ResponseEntity.ok("deleted successfully!");
    }

}
