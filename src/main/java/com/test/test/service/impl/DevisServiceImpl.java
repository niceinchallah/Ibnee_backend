package com.test.test.service.impl;

import com.test.test.DTO.*;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.*;
import com.test.test.mapper.DevisMapper;
import com.test.test.repository.DevisRepository;
import com.test.test.repository.Clientrep;
import com.test.test.repository.Entrepriserep;
import com.test.test.service.DevisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.corundumstudio.socketio.SocketIOServer;

@Service
public class DevisServiceImpl implements DevisService {

    private final DevisRepository devisRepository;
    private final Clientrep clientrep;
    private final Entrepriserep entrepriserep;
    private final SocketIOServer socketIOServer; // Injection de dépendance

    @Autowired
    public DevisServiceImpl(DevisRepository devisRepository, Clientrep clientrep, Entrepriserep entrepriserep, SocketIOServer socketIOServer) {
        this.devisRepository = devisRepository;
        this.clientrep = clientrep;
        this.entrepriserep = entrepriserep;
        this.socketIOServer = socketIOServer;
    }
    @Override
    public DevisDTO createDevis(DevisDTO devisDTO) {
        Devis devis = mapDevisDTOToEntity(devisDTO);
        devis.setStatus("COMPLETE");

        Devis savedDevis = devisRepository.save(devis);

        // Émettre un événement Socket.IO pour notifier la création du devis
        socketIOServer.getBroadcastOperations().sendEvent("newDevisEvent", savedDevis.getId());

        // Vérifier l'exploit 50 devis
        checkDevisMilestone();

        return DevisMapper.mapToDevisDTO(savedDevis);
    }

    // Autres méthodes du service Devis...

    private void checkDevisMilestone() {
        long totalDevis = devisRepository.count();
        if (totalDevis == 50) {
            socketIOServer.getBroadcastOperations().sendEvent("milestoneEvent", "50 devis ont été atteints !");
        }
    }
    @Override
    public double getTotalAmountDevis() {
        return devisRepository.findAll().stream().mapToDouble(Devis::getTotalAmount).sum();
    }
    @Override
    public DevisDTO createDevisDraft(DevisDTO devisDTO) {
        Devis devis = mapDevisDTOToEntity(devisDTO);
        devis.setStatus("DRAFT");

        Devis savedDevis = devisRepository.save(devis);
        return DevisMapper.mapToDevisDTO(savedDevis);
    }

    @Override
    public DevisDTO getDevisById(Long devisId) {
        Devis devis = devisRepository.findById(devisId)
                .orElseThrow(() -> new ResourceNotFoundException("Devis not found with given id: " + devisId));
        return DevisMapper.mapToDevisDTO(devis);
    }

    @Override
    public List<DevisDTO> getAllDevis() {
        List<Devis> devisList = devisRepository.findAll();
        return devisList.stream().map(DevisMapper::mapToDevisDTO).collect(Collectors.toList());
    }

    @Override
    public List<DevisDTO> getDevisByDraft(boolean draft) {
        List<Devis> devisList = devisRepository.findByStatus(draft ? "DRAFT" : "COMPLETE");
        return devisList.stream().map(DevisMapper::mapToDevisDTO).collect(Collectors.toList());
    }

    @Override
    public DevisDTO updateDevis(Long devisId, DevisDTO updatedDevisDTO) {
        Devis existingDevis = devisRepository.findById(devisId)
                .orElseThrow(() -> new ResourceNotFoundException("Devis not found with given id: " + devisId));

        updateDevisFromDTO(existingDevis, updatedDevisDTO);

        Devis updatedDevis = devisRepository.save(existingDevis);
        return DevisMapper.mapToDevisDTO(updatedDevis);
    }

    @Override
    public void deleteDevis(Long devisId) {
        Devis devis = devisRepository.findById(devisId)
                .orElseThrow(() -> new ResourceNotFoundException("Devis not found with given id: " + devisId));
        devisRepository.deleteById(devisId);
    }

    @Override
    public long countDevis() {
        return devisRepository.count();
    }

    private Devis mapDevisDTOToEntity(DevisDTO devisDTO) {
        Devis devis = new Devis();
        devis.setNumeroDeDevis(generateDevisNumber(devisDTO.getClientId()));
        devis.setDateDeDevis(devisDTO.getDateDeDevis());

        Entreprise entreprise = entrepriserep.findById(devisDTO.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found"));
        devis.setEntreprise(entreprise);

        Client client = clientrep.findById(devisDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        devis.setClient(client);

        for (ArticleDTO articleDTO : devisDTO.getArticles()) {
            Article article = new Article();
            article.setTitle(articleDTO.getTitle());
            article.setDescription(articleDTO.getDescription());
            article.setPrice(articleDTO.getPrice());
            article.setQuantity(articleDTO.getQuantity());
            article.setDevis(devis);
            devis.getArticles().add(article);
        }

        if (devisDTO.getImpots() != null) {
            for (ImpotDTO impotDTO : devisDTO.getImpots()) {
                Impot impot = new Impot();
                impot.setName(impotDTO.getName());
                impot.setPercentage(impotDTO.getPercentage());
                impot.setDevis(devis);
                devis.getImpots().add(impot);
            }
        }

        for (InformationBasDePageDTO infoDTO : devisDTO.getInformationsBasDePage()) {
            InformationBasDePage info = new InformationBasDePage();
            info.setCompanyDetails(infoDTO.getCompanyDetails());
            info.setContactDetails(infoDTO.getContactDetails());
            info.setBankDetails(infoDTO.getBankDetails());
            info.setDevis(devis);
            devis.getInformationsBasDePage().add(info);
        }

        for (SignatureDTO signatureDTO : devisDTO.getSignatures()) {
            Signature signature = new Signature();
            signature.setType(signatureDTO.getType());
            signature.setContent(signatureDTO.getContent());
            signature.setDevis(devis);
            devis.getSignatures().add(signature);
        }

        for (PDFStorageDTO pdfDTO : devisDTO.getPdfs()) {
            PDFStorage pdf = new PDFStorage();
            pdf.setFileName(pdfDTO.getFileName());
            pdf.setFileType(pdfDTO.getFileType());
            pdf.setData(pdfDTO.getData());
            pdf.setDevis(devis);
            devis.getPdfs().add(pdf);
        }

        return devis;
    }

    private void updateDevisFromDTO(Devis devis, DevisDTO updatedDevisDTO) {
        devis.setNumeroDeDevis(updatedDevisDTO.getNumeroDeDevis());
        devis.setDateDeDevis(updatedDevisDTO.getDateDeDevis());

        Client client = clientrep.findById(updatedDevisDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        Entreprise entreprise = entrepriserep.findById(updatedDevisDTO.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found"));

        devis.setClient(client);
        devis.setEntreprise(entreprise);

        devis.getArticles().clear();
        for (ArticleDTO articleDTO : updatedDevisDTO.getArticles()) {
            Article article = new Article();
            article.setTitle(articleDTO.getTitle());
            article.setDescription(articleDTO.getDescription());
            article.setPrice(articleDTO.getPrice());
            article.setQuantity(articleDTO.getQuantity());
            article.setDevis(devis);
            devis.getArticles().add(article);
        }

        devis.getImpots().clear();
        for (ImpotDTO impotDTO : updatedDevisDTO.getImpots()) {
            Impot impot = new Impot();
            impot.setName(impotDTO.getName());
            impot.setPercentage(impotDTO.getPercentage());
            impot.setDevis(devis);
            devis.getImpots().add(impot);
        }

        devis.getInformationsBasDePage().clear();
        for (InformationBasDePageDTO infoDTO : updatedDevisDTO.getInformationsBasDePage()) {
            InformationBasDePage info = new InformationBasDePage();
            info.setCompanyDetails(infoDTO.getCompanyDetails());
            info.setContactDetails(infoDTO.getContactDetails());
            info.setBankDetails(infoDTO.getBankDetails());
            info.setDevis(devis);
            devis.getInformationsBasDePage().add(info);
        }

        devis.getSignatures().clear();
        for (SignatureDTO signatureDTO : updatedDevisDTO.getSignatures()) {
            Signature signature = new Signature();
            signature.setType(signatureDTO.getType());
            signature.setContent(signatureDTO.getContent());
            signature.setDevis(devis);
            devis.getSignatures().add(signature);
        }

        devis.getPdfs().clear();
        for (PDFStorageDTO pdfDTO : updatedDevisDTO.getPdfs()) {
            PDFStorage pdf = new PDFStorage();
            pdf.setFileName(pdfDTO.getFileName());
            pdf.setFileType(pdfDTO.getFileType());
            pdf.setData(pdfDTO.getData());
            pdf.setDevis(devis);
            devis.getPdfs().add(pdf);
        }
    }

    private String generateDevisNumber(Long clientId) {
        Long devisCount = devisRepository.countByClientId(clientId);
        return "D-" + (devisCount + 1);
    }
    @Override
    public List<DevisDTO> getDevisByUser(String username) {
        return devisRepository.findByUser_Username(username).stream()
                .map(devis -> new DevisDTO(devis))
                .collect(Collectors.toList());
    }
}
