package com.test.test.controller;

import com.test.test.DTO.ClientDTO;
import com.test.test.service.Clientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Validated
public class Clientcontroller {

    @Autowired
    private Clientservice clientservice;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientservice.createClient(clientDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long clientId) {
        ClientDTO clientDTO = clientservice.getClientbyid(clientId);
        return ResponseEntity.ok(clientDTO);
    }
    @GetMapping("/count")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Long> countClients() {
        long count = clientservice.countClients();
        return ResponseEntity.ok(count);
    }
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientservice.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long clientId,
                                                  @RequestBody ClientDTO updatedClient) {
        ClientDTO clientDTO = clientservice.updateClients(clientId, updatedClient);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long clientId) {
        clientservice.deleteClients(clientId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
