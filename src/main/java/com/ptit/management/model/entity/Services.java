package com.ptit.management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "service")
@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Services implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "service_code")
    private String servicesCode;

    @Column(name = "name")
    private String servicesName;

    @Column(name = "type")
    private String servicesType;

    @Column(name = "unit_price")
    private double servicesUnitPrice;

////    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinTable(
////            joinColumns = @JoinColumn(name = "services_id"),
////            inverseJoinColumns = @JoinColumn(name = "company_id")
//    )
//    private List<Company> companies;
}

