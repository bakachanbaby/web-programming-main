package com.ptit.management.service.serviceCompany;

import com.ptit.management.mapper.serviceCompany.ServiceCompanyMapper;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyRequestDto;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyResponseDto;
import com.ptit.management.model.entity.Company;
import com.ptit.management.model.entity.ServiceCompany;
import com.ptit.management.model.entity.Services;
import com.ptit.management.repository.ServiceCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceCompanyServiceImpl implements ServiceCompanyService{
    private static final Logger logger = LoggerFactory.getLogger(ServiceCompanyServiceImpl.class);

    @Autowired
    private ServiceCompanyRepository repository;

    @Autowired
    private ServiceCompanyMapper mapper;


    @Override
    public ServiceCompanyResponseDto addServiceToCompany(ServiceCompanyRequestDto serviceCompanyRequestDto) {
        logger.info("Add new service company");
        ServiceCompany serviceCompany = mapper.requestMapToEntity(serviceCompanyRequestDto);
        ServiceCompany serviceCompanyAdded = repository.save(serviceCompany);
        logger.info("New service company added: {}", serviceCompanyAdded);
        return mapper.entityMapToResponse(serviceCompanyAdded);
    }

    @Override
    public ServiceCompanyResponseDto updateServiceCompany(ServiceCompanyRequestDto serviceCompanyRequestDto) {
        logger.info("Update current service company");
        ServiceCompany serviceCompany = mapper.requestMapToEntity(serviceCompanyRequestDto);
        ServiceCompany exist = repository.findById(serviceCompany.getId()).orElse(null);
        if(exist != null){
            exist = repository.saveAndFlush(serviceCompany);
        }
        logger.info("Update finished !!!!!!!!!!!");
        return mapper.entityMapToResponse(exist);
    }

    @Override
    public ServiceCompanyResponseDto getServiceCompanyById(int id) {
        logger.info("get service company by id");
        ServiceCompany serviceCompany = repository.findById(id).orElse(null);
        return mapper.entityMapToResponse(serviceCompany);
    }

    @Override
    public Boolean deleteServiceCompany(int id) {
        logger.info("Delete service company");
        ServiceCompany serviceCompany = repository.findById(id).orElse(null);
        if(serviceCompany != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceCompanyResponseDto> getAllServiceCompany(int id) {
        logger.info("Get all service company");
        List<ServiceCompany> serviceCompanyList = repository.findByCompany_Id(id);
        return mapper.entityMapToResponseList(serviceCompanyList);
    }

    @Override
    public void deleteByService(Services services) {
        logger.info("delete service_company with service :{}", services);
        List<ServiceCompany> serviceCompanyList = repository.findByService(services);
        if (serviceCompanyList.isEmpty()){
            repository.deleteByService(services);
            logger.info("delete success");
        }
        else {
            logger.warn("This service has not participated in any company yet.");
        }
    }

    @Override
    public void deleteByCompany(Company company) {
        logger.info("delete service_company with company :{}", company);
        List<ServiceCompany> serviceCompanyList = repository.findByCompany(company);
        if (serviceCompanyList.isEmpty()){
            repository.deleteByCompany(company);
            logger.info("delete success");
        }
        else {

            logger.warn("This company has not participated in any service yet.");
        }
    }

    @Override
    public void addServicesForNewCompany(ServiceCompany serviceCompany) {
        logger.info("add service for new company");
        repository.save(serviceCompany);
    }

    @Override
    public List<Services> getServiceListOfCompany(Company company) {
        List<Services> services = new ArrayList<>();
        List<ServiceCompany> serviceCompanyList = repository.findByCompany(company);// lấy từ csdl
        for (ServiceCompany serviceCompany : serviceCompanyList){
            services.add(serviceCompany.getService());
        }
        return services;
    }
}
