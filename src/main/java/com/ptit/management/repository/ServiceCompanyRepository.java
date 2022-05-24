package com.ptit.management.repository;

import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.ServiceCompany;
import com.ptit.management.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCompanyRepository extends JpaRepository<ServiceCompany, Integer> {
    List<ServiceCompany> findByCompany_Id(int id);

    int countByCompany(Company company);

    void deleteByCompany(Company company);

    void deleteByService(Services services);

    List<ServiceCompany> findByService(Services services);

    List<ServiceCompany> findByCompany(Company company);

}
