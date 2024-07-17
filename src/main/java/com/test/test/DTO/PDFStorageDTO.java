package com.test.test.DTO;

import com.test.test.entity.PDFStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PDFStorageDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] data;

    public PDFStorageDTO(PDFStorage pdfStorage) {
        this.id = pdfStorage.getId();
        this.fileName = pdfStorage.getFileName();
        this.fileType = pdfStorage.getFileType();
        this.data = pdfStorage.getData();
    }
}
