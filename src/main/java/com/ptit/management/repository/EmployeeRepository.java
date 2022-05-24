package com.ptit.management.repository;

import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.Employee;
//import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Long countByCompany(Company company);

    void deleteByCompany(Company company);

    List<Employee> findByCompany(Company company);
}
