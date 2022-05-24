package com.ptit.management.model.dto.serviceCompany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.company.CompanyResponseDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCompanyResponseDto implements Serializable {
    @JsonProperty("id")
    private int id;

    @JsonProperty("service_code")
    private String servicesCode;

    @JsonProperty("name")
    private String servicesName;

    @JsonProperty("type")
    private String servicesType;

    @JsonProperty("unit_price")
    private double servicesUnitPrice;

    @JsonProperty("month")
    private int month;
}
