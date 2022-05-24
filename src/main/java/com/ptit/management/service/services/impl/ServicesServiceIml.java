package com.ptit.management.service.services.impl;


import com.ptit.management.mapper.services.ServicesMapper;
import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.entity.Services;
import com.ptit.management.repository.ServicesRepository;
import com.ptit.management.service.serviceCompany.ServiceCompanyService;
import com.ptit.management.service.services.ServicesService;
import com.ptit.management.service.staffservice.StaffServicesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicesServiceIml implements ServicesService {

    private static final Logger logger = LoggerFactory.getLogger(ServicesServiceIml.class);

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private StaffServicesDao staffServicesDao;

    @Autowired
    private ServiceCompanyService serviceCompanyService;

    @Override
    public ServicesResponseDto addNewServices(ServicesRequestDto requestDto) {
        logger.info("starting add employee...");
        Services services = servicesMapper.convertToEntity(requestDto);
        Services ServicesAdded = servicesRepository.save(services);
        logger.info("employee is added: {}", ServicesAdded);
        logger.info("adding employee finish...");
        return servicesMapper.convertToResponseDto(ServicesAdded);
    }

    @Override
    public ServicesResponseDto updateServices(ServicesRequestDto requestDto) {
        logger.info("starting updating services...");
        Services servicesEntity = servicesMapper.convertToEntity(requestDto);
        Services services = servicesRepository.findById(servicesEntity.getId()).orElse(null);
        Services servicesUpdated = null;

        if (services != null){
            servicesUpdated = servicesRepository.saveAndFlush(servicesEntity);
            logger.info("service is updated: {}", servicesUpdated);
        }
        else {
            logger.error("Service not found...");
        }
        logger.info("updating services finish...");
        return servicesMapper.convertToResponseDto(servicesUpdated);
    }

    @Override
    public boolean deleteServices(int ServicesId) {
        logger.info("starting delete employee...");
        boolean resultDeleted = false;
        Services services = servicesRepository.findById(ServicesId).orElse(null);
        if (services != null){
            logger.info("delete all staff_service:");
            staffServicesDao.deleteByService(services);

            logger.info("delete all service_company:");
            serviceCompanyService.deleteByService(services);

            logger.info("delete employee: {}", services);
            servicesRepository.deleteById(ServicesId);
            resultDeleted = true;
        }
        return resultDeleted;
    }

    @Override
    public List<ServicesResponseDto> getAllServices() {
        logger.info("starting read list of employee...");
        List<Services> ServicesList = servicesRepository.findAll();
        logger.info("List of employees are read: {}", ServicesList);
        logger.info("reading list of employee finish...");
        return servicesMapper.convertToResponseDtoList(ServicesList);
    }

    @Override
    public ServicesResponseDto getServicesById(int ServicesId) {
        logger.info("starting read employee...");
        Services services = servicesRepository.findById(ServicesId).orElse(null);
        logger.info("employee is read: {}", services);
        logger.info("reading employee finish...");
        return servicesMapper.convertToResponseDto(services);
    }

    @Override
    public Services getByName(String name) {
        logger.info("find service with name:{}", name);
        return servicesRepository.findByServicesName(name);
    }
}


