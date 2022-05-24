package com.ptit.management.repository;

import com.ptit.management.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

    Services findByServicesName(String name);
}