package com.ptit.management.service.employee.iml;

import com.ptit.management.mapper.employee.EmployeeMapper;
import com.ptit.management.model.dto.employee.EmployeeRequestDto;
import com.ptit.management.model.dto.employee.EmployeeResponseDto;
import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.Employee;
import com.ptit.management.repository.EmployeeRepository;
import com.ptit.management.service.company.CompanyService;
import com.ptit.management.service.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceIml implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceIml.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CompanyService companyService;


    @Override
    public EmployeeResponseDto addNewEmployee(EmployeeRequestDto requestDto) {
        logger.info("adding employee of company");
        Employee employee = employeeMapper.convertToEntity(requestDto);
        Employee employeeAdded = employeeRepository.save(employee);
        if (employeeAdded != null){
            logger.info("Employee of company is added successful");
            return employeeMapper.convertToResponseDto(employeeAdded);
        }
        else {
            logger.error("Add employee of company fail.");
            return null;
        }
    }

    @Override
    public EmployeeResponseDto updateEmployee(EmployeeRequestDto requestDto) {
        logger.info("updating employee of company");
        Employee employee = employeeMapper.convertToEntity(requestDto);
        Employee existEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        if (existEmployee != null){
            existEmployee = employeeRepository.saveAndFlush(employee);
        }
        logger.info("finish updating employee of company");
        return employeeMapper.convertToResponseDto(existEmployee);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(int id) {
        logger.info("get employee of company by id");
        Employee employee = employeeRepository.findById(id).orElse(null);
        logger.info("finish ...");
        return employeeMapper.convertToResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        logger.info("get list employee of company");
        List<Employee> employeeList = employeeRepository.findAll();
        logger.info("finish ...");
        return employeeMapper.convertToResponseDtoList(employeeList);
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        logger.info("delete employee of company with id: {}", id);
        boolean result = false;
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null){
            employeeRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public Long getTheNumberOfEmployeeOfCompany(Company company) {
        logger.info("find the number of employee of the company");
        Long numberOfEmployee = employeeRepository.countByCompany(company);
        logger.info("finish...");
        return numberOfEmployee;
    }

    @Override
    public void deleteAllEmployeeOfCompany(Company company) {
        logger.info("delete all employee of company:");
        employeeRepository.deleteByCompany(company);
        logger.info("finish ....");
    }

    @Override
    public List<EmployeeResponseDto> getListEmployeeByCompanyId(int companyId) {
        logger.info("search list employee by company");
        List<Employee> employeeList = new ArrayList<>();
        logger.info("get company by id....");
        Company company = companyService.getById(companyId);
        if (company != null){
            logger.info("company not null");
            employeeList = employeeRepository.findByCompany(company);
            logger.info("list: {}", employeeList);
        }
        return employeeMapper.convertToResponseDtoList(employeeList);
    }

}
