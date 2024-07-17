package com.test.test.service.impl;

import com.test.test.DTO.EntrepriseDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Entreprise;
import com.test.test.entity.Entreprise;
import com.test.test.mapper.EntrepriseMapper;
import com.test.test.repository.Entrepriserep;
import com.test.test.service.Entrepriseservice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Entrepriseserviceimpl implements Entrepriseservice {
    private Entrepriserep Entrepriserep;
    @Override
    public EntrepriseDTO createEntreprise(EntrepriseDTO EntrepriseDTO) {
        Entreprise Entreprise= EntrepriseMapper.mapToEntreprise(EntrepriseDTO);
        Entreprise savedEntreprise=Entrepriserep.save(Entreprise);
        return EntrepriseMapper.mapToEntrepriseDTO(savedEntreprise);
    }

    @Override
    public EntrepriseDTO getEntreprisebyid(Long EntrepriseID) {
        Entreprise Entreprise =  Entrepriserep.findById(EntrepriseID)
                .orElseThrow(()->
                        new ResourceNotFoundException("not exist with given id "+ EntrepriseID));

        return EntrepriseMapper.mapToEntrepriseDTO(Entreprise);
    }

    @Override
    public List<EntrepriseDTO> getAllEntreprises() {
        List<Entreprise >  Entreprises = Entrepriserep.findAll();
        return Entreprises.stream().map((Entreprise) ->EntrepriseMapper.mapToEntrepriseDTO(Entreprise)  )
                .collect(Collectors.toList());
    }

    @Override
    public EntrepriseDTO updateEntreprises(Long Entrepriseid, EntrepriseDTO updatedEntreprises) {
        Entreprise Entreprise =    Entrepriserep.findById(Entrepriseid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with givfen id: "+ Entrepriseid)
        );
        Entreprise.setId(updatedEntreprises.getId());
        Entreprise.setName(updatedEntreprises.getName());
        Entreprise.setEmail(updatedEntreprises.getEmail());
        Entreprise.setAdress(updatedEntreprises.getAdress());
        Entreprise.setPhone(updatedEntreprises.getPhone());
        Entreprise.setBank(updatedEntreprises.getBank());
        Entreprise.setLogo(updatedEntreprises.getLogo());
        Entreprise.setDate(updatedEntreprises.getDate());
        Entreprise updatedEntrepriseObj = Entrepriserep.save(Entreprise);
        return EntrepriseMapper.mapToEntrepriseDTO(updatedEntrepriseObj);
    }

    @Override
    public void deleteEntreprises(Long Entrepriseid) {
        Entreprise Entreprise =    Entrepriserep.findById(Entrepriseid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with givfen id: "+ Entrepriseid)
        );
        Entrepriserep.deleteById(Entrepriseid);
    }
}
