package com.ptit.management.mapper.services;

import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.entity.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ServicesMapper {

    private static final Logger logger = LoggerFactory.getLogger(ServicesMapper.class);

    public ServicesResponseDto convertToResponseDto(Services services){
        ServicesResponseDto responseDto = null;
        if (services != null){
            responseDto = ServicesResponseDto.builder()
                    .id(services.getId())
                    .servicesCode(services.getServicesCode())
                    .servicesName(services.getServicesName())
                    .servicesType(services.getServicesType())
                    .servicesUnitPrice(services.getServicesUnitPrice())
                    .build();
            logger.info("convert entity of employee to response of services");
        }
        return responseDto;
    }

    public List<ServicesResponseDto> convertToResponseDtoList(List<Services> servicesList){
        List<ServicesResponseDto> responseDtoList = null;
        if (servicesList != null){
            responseDtoList = servicesList.stream().map(this::convertToResponseDto).collect(toList());
            logger.info("Convert list of employee to list of responseDto");
            logger.info("List of employee response: {}", responseDtoList);
        }
        return responseDtoList;
    }

    public Services convertToEntity(ServicesRequestDto requestDto){
        Services services = null;
        if (requestDto != null){
            services = Services.builder()
                    .id(requestDto.getId())
                    .servicesCode(requestDto.getServicesCode())
                    .servicesName(requestDto.getServicesName())
                    .servicesType(requestDto.getServicesType())
                    .servicesUnitPrice(requestDto.getServicesUnitPrice())
                    .build();
            logger.info("convert request of services:{} to entity of services:{}", requestDto, services);
        }
        return services;
    }


}
