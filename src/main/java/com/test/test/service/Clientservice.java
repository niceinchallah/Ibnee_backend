package com.test.test.service;

import com.test.test.DTO.ClientDTO;

import java.util.List;

public interface Clientservice {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientbyid(Long clientID);
    List<ClientDTO> getAllClients();
    ClientDTO updateClients(Long Clientid,ClientDTO updatedClients);
    void  deleteClients(Long Clientid);
    long countClients();
}
