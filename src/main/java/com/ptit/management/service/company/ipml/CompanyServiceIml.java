package com.ptit.management.service.company.ipml;

import com.ptit.management.mapper.company.CompanyMapper;
import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.company.CompanyResponseDto;
import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.ServiceCompany;
import com.ptit.management.model.entity.Services;
import com.ptit.management.repository.CompanyRepository;
import com.ptit.management.service.company.CompanyService;
import com.ptit.management.service.employee.EmployeeService;
import com.ptit.management.service.serviceCompany.ServiceCompanyService;
import com.ptit.management.service.services.ServicesService;
import com.ptit.management.util.CompanyPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiceIml implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceIml.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeService employeeCompanyService;

    @Autowired
    private ServiceCompanyService serviceCompanyService;

    @Autowired
    private CompanyPayment payment;

    @Autowired
    private ServicesService service;

    @Override
    public CompanyResponseDto addNewCompany(CompanyRequestDto requestDto) {
        logger.info("starting add company...");
        Company company = companyMapper.convertToEntity(requestDto);
        Company companyAdded = companyRepository.save(company);
        logger.info("employee is added: {}", companyAdded);
        logger.info("adding employee finish...");
//
//        logger.info("add service 'bao ve' & 've sinh' for company");
//        addDefaultServiceForCompanyBy("security", companyAdded);
//        addDefaultServiceForCompanyBy("cleaning", companyAdded);

        CompanyResponseDto companyResponseDto = companyMapper.convertToResponseDto(companyAdded);
        companyResponseDto.setAmountEmployee(0L);
        List<Services> services = getListServiceOfCompany(companyAdded);
        double usingFee = payment.calculateTotalRent(companyAdded.getGroundArea(), 0L, services);
        companyResponseDto.setUsingFee(usingFee);
        return companyResponseDto;
    }

    @Override
    public CompanyResponseDto updateCompany(CompanyRequestDto requestDto) {
        logger.info("starting updating employee...");
        Company companyEntity = companyMapper.convertToEntity(requestDto);
        Company company = companyRepository.findById(companyEntity.getId()).orElse(null);
        Company companyUpdated = null;
        if (company != null){
            companyUpdated = companyRepository.saveAndFlush(companyEntity);
            logger.info("employee is updated: {}", companyUpdated);
        }
        else {
            logger.error("Employee not found...");
        }
        logger.info("updating employee finish...");

        CompanyResponseDto companyResponseDto = companyMapper.convertToResponseDto(companyUpdated);
        Long amountOfEmployee = employeeCompanyService.getTheNumberOfEmployeeOfCompany(companyUpdated);
        companyResponseDto.setAmountEmployee(amountOfEmployee);

        List<Services> services = getListServiceOfCompany(companyUpdated);
        double usingFee = payment.calculateTotalRent(companyUpdated.getGroundArea(), amountOfEmployee, services);
        companyResponseDto.setUsingFee(usingFee);
        return companyResponseDto;
    }

    @Override
    public boolean deleteCompany(int companyId) {
        logger.info("starting delete employee...");
        boolean resultDeleted = false;
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null){
            logger.info("delete all employee of company before delete company:");
            employeeCompanyService.deleteAllEmployeeOfCompany(company);//xóa nhân viên
            logger.info("delete all service of company before delete company:");
            serviceCompanyService.deleteByCompany(company); //xóa dịch vụ mà cty đó thuê
            logger.info("delete company: {}", company);
            companyRepository.deleteById(companyId);
            resultDeleted = true;
        }
        return resultDeleted;
    }

    @Override
    public List<CompanyResponseDto> getAllCompany() {
        logger.info("starting read list of employee...");
        List<Company> companyList = companyRepository.findAll();
        logger.info("List of company are read: {}", companyList);
        logger.info("reading list of employee finish...");

        List<CompanyResponseDto> companyResponseList = new ArrayList<>();

        for (Company company : companyList){
            CompanyResponseDto  companyResponseDto = companyMapper.convertToResponseDto(company);
            Long amountOfEmployee = employeeCompanyService.getTheNumberOfEmployeeOfCompany(company);
            List<Services> services = getListServiceOfCompany(company);
            logger.info("List service of company: {}", services);
            double usingFees = payment.calculateTotalRent(company.getGroundArea(), amountOfEmployee, services);
            companyResponseDto.setAmountEmployee(amountOfEmployee);
            companyResponseDto.setUsingFee(usingFees);
            companyResponseList.add(companyResponseDto);
        }
        return companyResponseList;
    }

    @Override
    @Transactional
    public CompanyResponseDto getCompanyById(int companyId) {
        logger.info("starting read company...");
        Company company = companyRepository.findById(companyId).orElse(null);
        logger.info("employee is read: {}", company);
        logger.info("reading employee finish...");
        CompanyResponseDto responseDto = companyMapper.convertToResponseDto(company);
        if(responseDto != null){
            Long amountOfEmployee = employeeCompanyService.getTheNumberOfEmployeeOfCompany(company);
            List<Services> services = getListServiceOfCompany(company);
            double usingFee = payment.calculateTotalRent(company.getGroundArea(), amountOfEmployee, services);
            responseDto.setAmountEmployee(amountOfEmployee);
            responseDto.setUsingFee(usingFee);
        }
        return responseDto;
    }

    @Override
    public Company getById(int companyId) {
        logger.info("get company by id: {}", companyId);
        Company company = companyRepository.findById(companyId).orElse(null);
        return company;
    }

    private void addDefaultServiceForCompanyBy(String name, Company company){
        Services services = service.getByName(name);
        ServiceCompany serviceCompany = ServiceCompany.builder()
                .company(company)
                .service(services)
                .month(1)
                .build();
        serviceCompanyService.addServicesForNewCompany(serviceCompany);
    }

    private List<Services> getListServiceOfCompany(Company company){
        List<Services> services = serviceCompanyService.getServiceListOfCompany(company);
        return services;
    }
}
