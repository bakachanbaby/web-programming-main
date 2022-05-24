package com.ptit.management.model.dto.serviceCompany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCompanyRequestDto implements Serializable {
    @JsonProperty("id")
    private int id;

    @JsonProperty("company")
    private CompanyRequestDto companyRequestDto;

    @JsonProperty("service")
    private ServicesRequestDto serviceRequestDto;

    @JsonProperty("month")
    private int month;
}
