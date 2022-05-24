package com.ptit.management.service.staff;

import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staff.StaffResponseDto;

import java.util.List;

public interface StaffService {

    public StaffResponseDto addNewStaff(StaffRequestDto requestDto);

    public StaffResponseDto updateStaff(StaffRequestDto requestDto);

    public boolean deleteStaff(int employeeId);

    public List<StaffResponseDto> getAllStaff();

    public StaffResponseDto getStaffById(int employeeId);

}
