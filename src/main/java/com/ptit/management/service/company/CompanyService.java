package com.ptit.management.service.company;

import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.company.CompanyResponseDto;
import com.ptit.management.model.entity.Company;


import java.util.List;

public interface CompanyService {

    public  CompanyResponseDto addNewCompany(CompanyRequestDto requestDto);

    public  CompanyResponseDto updateCompany(CompanyRequestDto requestDto);

    public boolean deleteCompany(int companyId);

    public  List<CompanyResponseDto> getAllCompany();

    public  CompanyResponseDto getCompanyById(int companyId);

    public Company getById(int companyId);

}
