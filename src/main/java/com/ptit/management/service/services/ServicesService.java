package com.ptit.management.service.services;

import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.entity.Services;

import java.util.List;

public interface ServicesService {

    public  ServicesResponseDto addNewServices(ServicesRequestDto requestDto);

    public  ServicesResponseDto updateServices(ServicesRequestDto requestDto);

    public boolean deleteServices(int ServicesId);

    public  List<ServicesResponseDto> getAllServices();

    public  ServicesResponseDto getServicesById(int ServicesId);

    public Services getByName(String name);

}


