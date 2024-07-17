package com.test.test.service.impl;

import com.test.test.DTO.ProjetDTO;
import com.test.test.entity.Projet;
import com.test.test.mapper.Projetmapper;
import com.test.test.repository.Projetrep;
import com.test.test.service.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.corundumstudio.socketio.SocketIOServer;
@Service
@AllArgsConstructor
public class ProjetServiceimpl implements ProjetService {

    private final Projetrep projetrep;

    private final SocketIOServer socketIOServer; // Injectez votre bean SocketIOServer ici

    @Override
    public ProjetDTO createProjet(ProjetDTO projetDTO) {
        Projet projet = Projetmapper.toEntity(projetDTO);
        Projet savedProjet = projetrep.save(projet);

        // Émettre un événement Socket.IO pour notifier la création du projet
        socketIOServer.getBroadcastOperations().sendEvent("newProjetEvent", savedProjet.getId());

        // Vérifier l'exploit 50 projets
        checkProjetMilestone();

        return Projetmapper.toDTO(savedProjet);
    }

    // Autres méthodes du service Projet...

    private void checkProjetMilestone() {
        long totalProjets = projetrep.count();
        if (totalProjets == 50) {
            socketIOServer.getBroadcastOperations().sendEvent("milestoneEvent", "50 projets ont été atteints !");
        }
    }
    @Override
    public ProjetDTO getProjetById(Long projetId) {
        Projet projet = projetrep.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet not found with id: " + projetId));
        return Projetmapper.toDTO(projet);
    }
    @Override
    public long countProjets() {
        return projetrep.count();
    }
    @Override
    public List<ProjetDTO> getAllProjets() {
        List<Projet> projets = projetrep.findAll();
        return projets.stream().map(Projetmapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProjetDTO updateProjet(Long projetId, ProjetDTO updatedProjetDTO) {
        Projet projet = projetrep.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet not found with id: " + projetId));

        projet.setName(updatedProjetDTO.getName());
        projet.setDescription(updatedProjetDTO.getDescription());
        projet.setDebut(updatedProjetDTO.getDebut());
        projet.setFin(updatedProjetDTO.getFin());
        projet.setServicePrincipal(updatedProjetDTO.getServicePrincipal());
        projet.setClient(updatedProjetDTO.getClient());


        Projet updatedProjet = projetrep.save(projet);
        return Projetmapper.toDTO(updatedProjet);
    }

    @Override
    public void deleteProjet(Long projetId) {
        Projet projet = projetrep.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet not found with id: " + projetId));
        projetrep.deleteById(projetId);
    }
}
