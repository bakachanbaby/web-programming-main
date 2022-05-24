package com.ptit.management.service.staffservice;

import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceResponseDto;
import com.ptit.management.model.entity.Services;
import com.ptit.management.model.entity.Staff;

import java.util.List;

public interface StaffServicesDao {

    public StaffServiceResponseDto addNewStaffService(StaffServiceRequestDto requestDto);
    public StaffServiceResponseDto updateStaffService(StaffServiceRequestDto requestDto);
    public List<StaffServiceResponseDto> getServicesByStaff(StaffRequestDto staffRequestDto);
    public List<StaffServiceResponseDto> getStaffByService(ServicesRequestDto servicesRequestDto);
    public boolean deleteById(int staffServiceId);
    public void deleteByStaff(Staff staff);
    public void deleteByService(Services services);
}
