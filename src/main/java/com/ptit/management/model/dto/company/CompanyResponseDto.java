package com.ptit.management.model.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponseDto implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("tax_number")
    private String taxNumber;

    @JsonProperty("authorized_capital")
    private double authorizedCapital;

    @JsonProperty("field_of_operation")
    private String fieldOfOperation;

    @JsonProperty("address_in_building")
    private String addressInBuilding;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("ground_area")
    private int groundArea;

    @JsonProperty("amount_employee")
    private Long amountEmployee;

    @JsonProperty("using_fee")
    private double usingFee;
}
