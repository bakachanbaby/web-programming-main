package com.ptit.management.model.dto.staff;

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
public class StaffRequestDto implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String staffName;

    @JsonProperty("address")
    private String staffAddress;

    @JsonProperty("code")
    private String staffCode;

    @JsonProperty("date_of_birth")
    private Date staffDob;

    @JsonProperty("phone_number")
    private String staffPhoneNumber;

}
