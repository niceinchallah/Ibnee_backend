package com.test.test.DTO;

import com.test.test.entity.InformationBasDePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformationBasDePageDTO {
    private Long id;
    private String additionnel;
    private String companyDetails;
    private String contactDetails;
    private String bankDetails;

    public InformationBasDePageDTO(InformationBasDePage information) {
        this.id = information.getId();
        this.additionnel = information.getAdditionnel();
        this.companyDetails = information.getCompanyDetails();
        this.contactDetails = information.getContactDetails();
        this.bankDetails = information.getBankDetails();
    }
}

