package com.ptit.management.model.dto.staffservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.dto.staff.StaffResponseDto;
import com.ptit.management.model.entity.Services;
import com.ptit.management.model.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffServiceResponseDto implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("staff_salary")
    private double salary;

    @JsonProperty("staff_position")
    private String position;

    @JsonProperty("staff_level")
    private String level;

    @JsonProperty("service")
    private ServicesResponseDto services;

    @JsonProperty("staff")
    private StaffResponseDto staff;
}
