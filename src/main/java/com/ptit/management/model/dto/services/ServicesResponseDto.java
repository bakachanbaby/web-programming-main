package com.ptit.management.model.dto.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesResponseDto implements Serializable {

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

}
