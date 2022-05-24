package com.ptit.management.service.serviceCompany;

import com.ptit.management.model.dto.serviceCompany.ServiceCompanyRequestDto;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyResponseDto;
import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.ServiceCompany;
import com.ptit.management.model.entity.Services;

import java.util.List;

public interface ServiceCompanyService {
    ServiceCompanyResponseDto addServiceToCompany(ServiceCompanyRequestDto serviceCompanyRequestDto);

    ServiceCompanyResponseDto updateServiceCompany(ServiceCompanyRequestDto serviceCompanyRequestDto);

    ServiceCompanyResponseDto getServiceCompanyById(int id);

    Boolean deleteServiceCompany(int id);

    List<ServiceCompanyResponseDto> getAllServiceCompany(int id);

    void deleteByService(Services services);
    void deleteByCompany(Company company);

    void addServicesForNewCompany(ServiceCompany serviceCompany);

    List<Services> getServiceListOfCompany(Company company);
}
