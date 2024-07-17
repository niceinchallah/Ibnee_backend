package com.test.test.service.impl;

import com.test.test.DTO.ClientDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Client;
import com.test.test.mapper.Clientmapper;
import com.test.test.repository.Clientrep;
import com.test.test.service.Clientservice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.corundumstudio.socketio.SocketIOServer;

@Service
@AllArgsConstructor
public class Clientserviceimpl implements Clientservice {
    private final Clientrep clientrep;
    private final SocketIOServer socketIOServer;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = Clientmapper.mapToClient(clientDTO);
        Client savedClient = clientrep.save(client);

        // Émettre un événement Socket.IO pour notifier la création du client
        socketIOServer.getBroadcastOperations().sendEvent("newClientEvent", savedClient.getId());

        // Vérifier l'exploit 50 clients
        checkClientMilestone();

        return Clientmapper.mapToClientDTO(savedClient);
    }

    // Autres méthodes du service Client...

    private void checkClientMilestone() {
        long totalClients = clientrep.count();
        if (totalClients == 50) {
            socketIOServer.getBroadcastOperations().sendEvent("milestoneEvent", "50 clients ont été atteints !");
        }
    }
    @Override
    public long countClients() {
        return clientrep.count();
    }
    @Override
    public ClientDTO getClientbyid(Long clientID) {
        Client client = clientrep.findById(clientID)
                .orElseThrow(() ->
                        new ResourceNotFoundException("not exist with given id " + clientID));

        return Clientmapper.mapToClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientrep.findAll();
        return clients.stream().map((client) -> Clientmapper.mapToClientDTO(client))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClients(Long Clientid, ClientDTO updatedClients) {
        Client client = clientrep.findById(Clientid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with given id: " + Clientid)
        );
        client.setId(updatedClients.getId());
        client.setName(updatedClients.getName());
        client.setEmail(updatedClients.getEmail());
        client.setAddress(updatedClients.getAddress());
        client.setPhone(updatedClients.getPhone());
        client.setBank(updatedClients.getBank());
        client.setIndicatif(updatedClients.getIndicatif());
        client.setDate(updatedClients.getDate());

        Client updatedClientObj = clientrep.save(client);
        return Clientmapper.mapToClientDTO(updatedClientObj);
    }

    @Override
    public void deleteClients(Long Clientid) {
        Client client = clientrep.findById(Clientid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with given id: " + Clientid)
        );
        clientrep.deleteById(Clientid);
    }
}
