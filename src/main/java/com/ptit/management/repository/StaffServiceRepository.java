package com.ptit.management.repository;

import com.ptit.management.model.entity.Services;
import com.ptit.management.model.entity.Staff;
import com.ptit.management.model.entity.StaffServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffServiceRepository extends JpaRepository<StaffServices, Integer> {

    void deleteByStaff(Staff staff);
    List<StaffServices> findByStaff(Staff staff);
    List<StaffServices> findByServices(Services services);
    void deleteByServices(Services services);
}
