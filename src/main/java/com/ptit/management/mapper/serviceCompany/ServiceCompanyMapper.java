package com.ptit.management.mapper.serviceCompany;

import com.ptit.management.mapper.company.CompanyMapper;
import com.ptit.management.mapper.services.ServicesMapper;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyRequestDto;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyResponseDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.entity.ServiceCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceCompanyMapper {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCompanyMapper.class);

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ServicesMapper serviceMapper;

    public ServiceCompany requestMapToEntity(ServiceCompanyRequestDto requestDto){
        ServiceCompany serviceCompany = null;
        if(requestDto != null){
            serviceCompany = ServiceCompany.builder()
                    .id(requestDto.getId())
                    .company(companyMapper.convertToEntity(requestDto.getCompanyRequestDto()))
                    .service(serviceMapper.convertToEntity(requestDto.getServiceRequestDto()))
                    .month(requestDto.getMonth())
                    .build();
            logger.info("Map ServiceCompany from request {} into {}", requestDto, serviceCompany);
        }
        return serviceCompany;
    }

    public ServiceCompanyResponseDto entityMapToResponse(ServiceCompany entity){
        ServiceCompanyResponseDto responseDto = null;
        if(entity != null){
            ServicesResponseDto service = serviceMapper.convertToResponseDto(entity.getService());
            responseDto = ServiceCompanyResponseDto.builder()
                    .id(entity.getId())
                    .servicesCode(service.getServicesCode())
                    .servicesName(service.getServicesName())
                    .servicesType(service.getServicesType())
                    .servicesUnitPrice(service.getServicesUnitPrice())
                    .month(entity.getMonth())
                    .build();
            logger.info("Map ServiceCompanyResponse from entity {} into {}", entity, responseDto);
        }

        return responseDto;
    }

    public List<ServiceCompanyResponseDto> entityMapToResponseList(List<ServiceCompany> entities){
        List<ServiceCompanyResponseDto> result = null;
        if(entities != null && !entities.isEmpty()){
            result = entities.stream().map(this::entityMapToResponse).collect(Collectors.toList());
            logger.info("List of response service company {}", result);
        }
        return result;
    }
}
