package com.test.test.service;
import com.test.test.DTO.materialDTO;

import java.util.List;

public interface materialservice {
    materialDTO creatematerial(materialDTO materialDTO);
  materialDTO getmaterialbyid(Long materialid);
  List<materialDTO> getAllmaterials();
  materialDTO updatematerials(Long materialid,materialDTO updatedmaterials);
  void deletematerial(Long materialid);
}
