package com.ptit.management.mapper.employee;


import com.ptit.management.mapper.company.CompanyMapper;
import com.ptit.management.model.dto.employee.EmployeeRequestDto;
import com.ptit.management.model.dto.employee.EmployeeResponseDto;
import com.ptit.management.model.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class EmployeeMapper {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMapper.class);

    @Autowired
    private CompanyMapper companyMapper;

    public Employee convertToEntity(EmployeeRequestDto employeeRequestDto){
        Employee employee = null;
        if (employeeRequestDto != null){
            employee = Employee.builder()
                    .id(employeeRequestDto.getId())
                    .employeeCode(employeeRequestDto.getEmployeeCode())
                    .employeeDob(employeeRequestDto.getEmployeeDob())
                    .employeeIdCard(employeeRequestDto.getEmployeeIdCard())
                    .employeePhoneNumber(employeeRequestDto.getPhoneNumber())
                    .employeeName(employeeRequestDto.getEmployeeName())
                    .company(companyMapper.convertToEntity(employeeRequestDto.getCompanyRequestDto()))
                    .build();
        }
        logger.info("employee: {}", employee);
        return employee;
    }

    public EmployeeResponseDto convertToResponseDto(Employee employee){
        EmployeeResponseDto responseDto = null;
        if (employee != null){
            responseDto = EmployeeResponseDto.builder()
                    .id(employee.getId())
                    .employeeCode(employee.getEmployeeCode())
                    .employeeDob(employee.getEmployeeDob())
                    .employeeIdCard(employee.getEmployeeIdCard())
                    .employeeName(employee.getEmployeeName())
                    .phoneNumber(employee.getEmployeePhoneNumber())
                    .build();
        }
        return responseDto;
    }

    public List<EmployeeResponseDto> convertToResponseDtoList(List<Employee> employeeCompanies){
        if (employeeCompanies != null){
            return employeeCompanies.stream().map(this::convertToResponseDto).collect(toList());
        }
        return null;
    }

}
