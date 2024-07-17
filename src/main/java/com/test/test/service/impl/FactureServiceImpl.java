package com.test.test.service.impl;
import com.test.test.DTO.*;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.*;
import com.test.test.mapper.FactureMapper;
import com.test.test.repository.Clientrep;
import com.test.test.repository.Entrepriserep;
import com.test.test.repository.FactureRepository;
import com.test.test.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corundumstudio.socketio.SocketIOServer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final Clientrep clientRep;
    private final Entrepriserep entrepriseRep;
    private final SocketIOServer socketIOServer; // Injection de dépendance

    @Autowired
    public FactureServiceImpl(FactureRepository factureRepository, Clientrep clientRep, Entrepriserep entrepriseRep, SocketIOServer socketIOServer) {
        this.factureRepository = factureRepository;
        this.clientRep = clientRep;
        this.entrepriseRep = entrepriseRep;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public FactureDTO createFacture(FactureDTO factureDTO) {
        Facture facture = mapFactureDTOToEntity(factureDTO);
        facture.setStatus("COMPLETE");

        Facture savedFacture = factureRepository.save(facture);

        // Émettre un événement Socket.IO pour notifier la création de la facture
        socketIOServer.getBroadcastOperations().sendEvent("newFactureEvent", factureDTO.getId());

        // Vérifier si la date d'échéance est dans 10 jours
        LocalDate deadline = factureDTO.getDateEcheance();
        LocalDate today = LocalDate.now();
        long daysUntilDeadline = ChronoUnit.DAYS.between(today, deadline);

        if (daysUntilDeadline == 10 || daysUntilDeadline == 5) {
            String message = String.format("Échéance proche de Facture %s dans %d jours", factureDTO.getId(), daysUntilDeadline);
            socketIOServer.getBroadcastOperations().sendEvent("factureDeadlineEvent", message);
        }

        return FactureMapper.mapToFactureDTO(savedFacture);
    }

    // Autres méthodes du service Facture...

    private void checkFactureMilestone() {
        long totalFactures = factureRepository.count();
        if (totalFactures == 50) {
            socketIOServer.getBroadcastOperations().sendEvent("milestoneEvent", "50 factures ont été atteintes !");
        }
    }
    @Override
    public FactureDTO createFactureDraft(FactureDTO factureDTO) {
        Facture facture = mapFactureDTOToEntity(factureDTO);
        facture.setStatus("DRAFT");

        Facture savedFacture = factureRepository.save(facture);
        return FactureMapper.mapToFactureDTO(savedFacture);
    }
    @Override
    public double getTotalAmountFactures() {
        return factureRepository.findAll().stream().mapToDouble(Facture::getTotalAmount).sum();
    }
    @Override
    public FactureDTO getFactureById(Long factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture not found with given id: " + factureId));
        return FactureMapper.mapToFactureDTO(facture);
    }

    @Override
    public List<FactureDTO> getAllFactures() {
        List<Facture> factureList = factureRepository.findAll();
        return factureList.stream().map(FactureMapper::mapToFactureDTO).collect(Collectors.toList());
    }

    @Override
    public FactureDTO updateFacture(Long factureId, FactureDTO updatedFactureDTO) {
        Facture existingFacture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture not found with given id: " + factureId));

        updateFactureFromDTO(existingFacture, updatedFactureDTO);

        Facture updatedFacture = factureRepository.save(existingFacture);
        return FactureMapper.mapToFactureDTO(updatedFacture);
    }

    @Override
    public void deleteFacture(Long factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture not found with given id: " + factureId));
        factureRepository.deleteById(factureId);
    }

    @Override
    public List<FactureDTO> getFacturesByDraft(boolean draft) {
        List<Facture> factures = factureRepository.findByStatus(draft ? "DRAFT" : "COMPLETE");
        return factures.stream().map(FactureMapper::mapToFactureDTO).collect(Collectors.toList());
    }

    @Override
    public long countFactures() {
        return factureRepository.count();
    }

    private Facture mapFactureDTOToEntity(FactureDTO factureDTO) {
        Facture facture = new Facture();
        facture.setNumeroDeFacture(generateFactureNumber(factureDTO.getClientId()));
        facture.setDateDeFacture(factureDTO.getDateDeFacture());
        facture.setDateDEcheance(factureDTO.getDateEcheance());

        Client client = clientRep.findById(factureDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        Entreprise entreprise = entrepriseRep.findById(factureDTO.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found"));

        facture.setClient(client);
        facture.setEntreprise(entreprise);

        // Mapping articles
        for (ArticleDTO articleDTO : factureDTO.getArticles()) {
            Article article = new Article();
            article.setTitle(articleDTO.getTitle());
            article.setDescription(articleDTO.getDescription());
            article.setPrice(articleDTO.getPrice());
            article.setQuantity(articleDTO.getQuantity());
            article.setFacture(facture);
            facture.getArticles().add(article);
        }

        // Mapping des impôts (optionnels)
        if (factureDTO.getImpots() != null) {
            for (ImpotDTO impotDTO : factureDTO.getImpots()) {
                Impot impot = new Impot();
                impot.setName(impotDTO.getName());
                impot.setPercentage(impotDTO.getPercentage());
                impot.setFacture(facture);
                facture.getImpots().add(impot);
            }
        }

        // Mapping des informations bas de page
        for (InformationBasDePageDTO infoDTO : factureDTO.getInformationsBasDePage()) {
            InformationBasDePage info = new InformationBasDePage();
            info.setCompanyDetails(infoDTO.getCompanyDetails());
            info.setContactDetails(infoDTO.getContactDetails());
            info.setBankDetails(infoDTO.getBankDetails());
            info.setFacture(facture);
            facture.getInformationsBasDePage().add(info);
        }

        // Mapping des signatures
        for (SignatureDTO signatureDTO : factureDTO.getSignatures()) {
            Signature signature = new Signature();
            signature.setType(signatureDTO.getType());
            signature.setContent(signatureDTO.getContent());
            signature.setFacture(facture);
            facture.getSignatures().add(signature);
        }

        // Mapping des PDFs
        for (PDFStorageDTO pdfDTO : factureDTO.getPdfs()) {
            PDFStorage pdf = new PDFStorage();
            pdf.setFileName(pdfDTO.getFileName());
            pdf.setFileType(pdfDTO.getFileType());
            pdf.setData(pdfDTO.getData());
            pdf.setFacture(facture);
            facture.getPdfs().add(pdf);
        }

        return facture;
    }

    private void updateFactureFromDTO(Facture facture, FactureDTO updatedFactureDTO) {
        facture.setNumeroDeFacture(updatedFactureDTO.getNumeroDeFacture());
        facture.setDateDeFacture(updatedFactureDTO.getDateDeFacture());
        facture.setDateDEcheance(updatedFactureDTO.getDateEcheance());

        Client client = clientRep.findById(updatedFactureDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        Entreprise entreprise = entrepriseRep.findById(updatedFactureDTO.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found"));

        facture.setClient(client);
        facture.setEntreprise(entreprise);

        // Clear and re-add articles
        facture.getArticles().clear();
        for (ArticleDTO articleDTO : updatedFactureDTO.getArticles()) {
            Article article = new Article();
            article.setTitle(articleDTO.getTitle());
            article.setDescription(articleDTO.getDescription());
            article.setPrice(articleDTO.getPrice());
            article.setQuantity(articleDTO.getQuantity());
            article.setFacture(facture);
            facture.getArticles().add(article);
        }

        // Clear and re-add impôts
        facture.getImpots().clear();
        for (ImpotDTO impotDTO : updatedFactureDTO.getImpots()) {
            Impot impot = new Impot();
            impot.setName(impotDTO.getName());
            impot.setPercentage(impotDTO.getPercentage());
            impot.setFacture(facture);
            facture.getImpots().add(impot);
        }

        // Clear and re-add informations bas de page
        facture.getInformationsBasDePage().clear();
        for (InformationBasDePageDTO infoDTO : updatedFactureDTO.getInformationsBasDePage()) {
            InformationBasDePage info = new InformationBasDePage();
            info.setCompanyDetails(infoDTO.getCompanyDetails());
            info.setContactDetails(infoDTO.getContactDetails());
            info.setBankDetails(infoDTO.getBankDetails());
            info.setFacture(facture);
            facture.getInformationsBasDePage().add(info);
        }

        // Clear and re-add signatures
        facture.getSignatures().clear();
        for (SignatureDTO signatureDTO : updatedFactureDTO.getSignatures()) {
            Signature signature = new Signature();
            signature.setType(signatureDTO.getType());
            signature.setContent(signatureDTO.getContent());
            signature.setFacture(facture);
            facture.getSignatures().add(signature);
        }

        // Clear and re-add PDFs
        facture.getPdfs().clear();
        for (PDFStorageDTO pdfDTO : updatedFactureDTO.getPdfs()) {
            PDFStorage pdf = new PDFStorage();
            pdf.setFileName(pdfDTO.getFileName());
            pdf.setFileType(pdfDTO.getFileType());
            pdf.setData(pdfDTO.getData());
            pdf.setFacture(facture);
            facture.getPdfs().add(pdf);
        }
    }

    private String generateFactureNumber(Long clientId) {
        Long factureCount = factureRepository.countByClientId(clientId);
        return "F-" + (factureCount + 1);
    }
    @Override
    public List<FactureDTO> getFacturesByUser(String username) {
        return factureRepository.findByUser_Username(username).stream()
                .map(facture -> new FactureDTO(facture))
                .collect(Collectors.toList());
    }
}
