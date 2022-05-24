package com.ptit.management.mapper.staff;

import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staff.StaffResponseDto;
import com.ptit.management.model.entity.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class StaffMapper {

    private static final Logger logger = LoggerFactory.getLogger(StaffMapper.class);

    public StaffResponseDto convertToResponseDto(Staff staff){
        StaffResponseDto responseDto = null;
        if (staff != null){
            responseDto = StaffResponseDto.builder()
                    .id(staff.getId())
                    .staffAddress(staff.getStaffAddress())
                    .staffCode(staff.getStaffCode())
                    .staffDob(staff.getStaffDob())
                    .staffName(staff.getStaffName())
                    .staffPhoneNumber(staff.getStaffPhoneNumber())
                    .build();
            logger.info("convert entity of staff to response of staff");
        }
        return responseDto;
    }

    public List<StaffResponseDto> convertToResponseDtoList(List<Staff> staffList){
        List<StaffResponseDto> responseDtoList = null;
        if (staffList != null){
            responseDtoList = staffList.stream().map(this::convertToResponseDto).collect(toList());
            logger.info("Convert list of staff to list of responseDto");
            logger.info("List of staff response: {}", responseDtoList);
        }
        return responseDtoList;
    }

    public Staff convertToEntity(StaffRequestDto requestDto){
        Staff staff = null;
        if (requestDto != null){
            staff = Staff.builder()
                    .id(requestDto.getId())
                    .staffAddress(requestDto.getStaffAddress())
                    .staffCode(requestDto.getStaffCode())
                    .staffDob(requestDto.getStaffDob())
                    .staffName(requestDto.getStaffName())
                    .staffPhoneNumber(requestDto.getStaffPhoneNumber())
                    .build();
            logger.info("convert request of staff:{} to entity of staff:{}", requestDto, staff);
        }
        return staff;
    }

}
