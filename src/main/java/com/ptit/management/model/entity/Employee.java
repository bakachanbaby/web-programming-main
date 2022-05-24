package com.ptit.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Builder
@Entity
@Table(name = "employee_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "employee_company_code")
    private String employeeCode;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "id_card")
    private String employeeIdCard;

    @Column(name = "date_of_birth")
    private Date employeeDob;

    @Column(name = "phone_number")
    private String employeePhoneNumber;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
