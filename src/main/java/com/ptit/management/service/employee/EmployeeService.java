package com.ptit.management.service.employee;

import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.employee.EmployeeRequestDto;
import com.ptit.management.model.dto.employee.EmployeeResponseDto;
import com.ptit.management.model.entity.Company;

import java.util.List;

public interface EmployeeService {

    public EmployeeResponseDto addNewEmployee(EmployeeRequestDto requestDto);

    public EmployeeResponseDto updateEmployee(EmployeeRequestDto requestDto);

    public EmployeeResponseDto getEmployeeById(int id);

    public List<EmployeeResponseDto> getAll();

    public boolean deleteEmployeeById(int id);

    public Long getTheNumberOfEmployeeOfCompany(Company company);

    public void deleteAllEmployeeOfCompany(Company company);

    public List<EmployeeResponseDto> getListEmployeeByCompanyId(int companyId);

}
