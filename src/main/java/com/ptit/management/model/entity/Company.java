package com.ptit.management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "authorized_capital")
    private double authorizedCapital;

    @Column(name = "field_of_operation")
    private String fieldOfOperation;

    @Column(name = "address_in_building")
    private String addressInBuilding;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "ground_area")
    private int groundArea;
}
