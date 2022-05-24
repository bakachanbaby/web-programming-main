package com.ptit.management.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "staff")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String staffName;

    @Column(name = "address")
    private String staffAddress;

    @Column(name = "staff_code")
    private String staffCode;

    @Column(name = "date_of_birth")
    private Date staffDob;

    @Column(name = "phone_number")
    private String staffPhoneNumber;
}
