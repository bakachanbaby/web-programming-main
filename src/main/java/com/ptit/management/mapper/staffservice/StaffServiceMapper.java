package com.ptit.management.mapper.staffservice;

import com.ptit.management.mapper.services.ServicesMapper;
import com.ptit.management.mapper.staff.StaffMapper;
import com.ptit.management.model.dto.staffservice.StaffServiceRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceResponseDto;
import com.ptit.management.model.entity.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class StaffServiceMapper {

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private StaffMapper staffMapper;

    public StaffServices convertToEntity(StaffServiceRequestDto requestDto){
        StaffServices staffServices = null;
        if (requestDto != null){
            staffServices = StaffServices.builder()
                    .id(requestDto.getId())
                    .services(servicesMapper.convertToEntity(requestDto.getServices()))
                    .staff(staffMapper.convertToEntity(requestDto.getStaff()))
                    .level(requestDto.getLevel())
                    .position(requestDto.getPosition())
                    .salary(requestDto.getSalary())
                    .build();
        }
        return staffServices;
    }

    public StaffServiceResponseDto convertToResponseDto(StaffServices staffServices){
        StaffServiceResponseDto responseDto = null;
        if (staffServices != null){
            responseDto = StaffServiceResponseDto.builder()
                    .id(staffServices.getId())
                    .level(staffServices.getLevel())
                    .position(staffServices.getPosition())
                    .salary(staffServices.getSalary())
                    .services(servicesMapper.convertToResponseDto(staffServices.getServices()))
                    .staff(staffMapper.convertToResponseDto(staffServices.getStaff()))
                    .build();
        }
        return responseDto;
    }

    public List<StaffServiceResponseDto> convertToResponseDtoList(List<StaffServices> staffServicesList){
        List<StaffServiceResponseDto> responseDtoList = null;
        if (staffServicesList != null){
            responseDtoList = staffServicesList.stream().map(this::convertToResponseDto).collect(toList());
        }
        return responseDtoList;
    }

}
