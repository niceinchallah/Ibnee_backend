package com.test.test.service.impl;
import com.test.test.DTO.materialDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.service.materialservice;
import com.test.test.entity.materialsent;
import com.test.test.mapper.materialsmapper;
import com.test.test.repository.materialrep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class materialserviceimpl implements materialservice {
private materialrep materialrep;
    @Override
    public materialDTO creatematerial(materialDTO materialDTO) {
        materialsent materialsent = materialsmapper.mapTomaterialsent(materialDTO);
        materialsent savedmaterial = materialrep.save(materialsent);
        return materialsmapper.mapTomaterialDTO(savedmaterial);
    }

    @Override
    public materialDTO getmaterialbyid(Long materialid) {
        materialsent materialsent = materialrep.findById(materialid)
                .orElseThrow(()->

                new ResourceNotFoundException("not exist with given id "+ materialid));

        return materialsmapper.mapTomaterialDTO(materialsent);
    }

    @Override
    public List<materialDTO> getAllmaterials() {
        return null;
    }

    @Override
    public materialDTO updatematerials(Long materialid, materialDTO updatedmaterials) {
        materialsent materialsent = materialrep.findById(materialid).orElseThrow(()
        -> new ResourceNotFoundException("not exist with given id :" +materialid)
                );
        materialsent.setId(updatedmaterials.getId());
        materialsent.setName(updatedmaterials.getName());
        materialsent.setPrice(updatedmaterials.getPrice());
        materialsent.setDate(updatedmaterials.getDate());
            materialsent updatedmaterialObj = materialrep.save(materialsent);
        return materialsmapper.mapTomaterialDTO(updatedmaterialObj);
    }

    @Override
    public void deletematerial(Long materialid) {
        materialsent materialsent = materialrep.findById(materialid).orElseThrow(()
                -> new ResourceNotFoundException("not exist with given id :" +materialid)
        );
        materialrep.deleteById(materialid);
    }
}
