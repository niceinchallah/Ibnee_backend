package com.test.test.controller;

import com.test.test.DTO.InformationBasDePageDTO;
import com.test.test.service.InformationBasDePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/information-bas-de-page")
@Validated
public class InformationBasDePagecontroller {

    @Autowired
    private InformationBasDePageService informationBasDePageService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<InformationBasDePageDTO> createInformationBasDePage(@RequestBody InformationBasDePageDTO informationBasDePageDTO) {
        InformationBasDePageDTO savedInformationBasDePage = informationBasDePageService.createInformationBasDePage(informationBasDePageDTO);
        return new ResponseEntity<>(savedInformationBasDePage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<InformationBasDePageDTO> getInformationBasDePageById(@PathVariable("id") Long informationBasDePageId) {
        InformationBasDePageDTO informationBasDePageDTO = informationBasDePageService.getInformationBasDePageById(informationBasDePageId);
        return ResponseEntity.ok(informationBasDePageDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<InformationBasDePageDTO>> getAllInformationBasDePage() {
        List<InformationBasDePageDTO> informationBasDePageList = informationBasDePageService.getAllInformationBasDePage();
        return ResponseEntity.ok(informationBasDePageList);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<InformationBasDePageDTO> updateInformationBasDePage(@PathVariable("id") Long informationBasDePageId,
                                                                              @RequestBody InformationBasDePageDTO updatedInformationBasDePage) {
        InformationBasDePageDTO informationBasDePageDTO = informationBasDePageService.updateInformationBasDePage(informationBasDePageId, updatedInformationBasDePage);
        return ResponseEntity.ok(informationBasDePageDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteInformationBasDePage(@PathVariable("id") Long informationBasDePageId) {
        informationBasDePageService.deleteInformationBasDePage(informationBasDePageId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
