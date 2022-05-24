package com.ptit.management.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("employee_code")
    private String employeeCode;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("id_card")
    private String employeeIdCard;

    @JsonProperty("date_of_birth")
    private Date employeeDob;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
