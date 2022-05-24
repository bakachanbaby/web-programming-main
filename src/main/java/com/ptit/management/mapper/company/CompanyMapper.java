package com.ptit.management.mapper.company;

import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.company.CompanyResponseDto;
import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CompanyMapper {

    private static final Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

    public CompanyResponseDto convertToResponseDto(Company company){
        CompanyResponseDto responseDto = null;
        if (company != null){
            responseDto = CompanyResponseDto.builder()
                    .id(company.getId())
                    .addressInBuilding(company.getAddressInBuilding())
                    .authorizedCapital(company.getAuthorizedCapital())
                    .companyName(company.getCompanyName())
                    .fieldOfOperation(company.getFieldOfOperation())
                    .groundArea(company.getGroundArea())
                    .phoneNumber(company.getPhoneNumber())
                    .taxNumber(company.getTaxNumber())
                    .build();
        }
        return responseDto;
    }

    public List<CompanyResponseDto> convertToResponseDtoList(List<Company> companyList){
        List<CompanyResponseDto> responseDtoList = null;
        if (companyList != null){
            responseDtoList = companyList.stream().map(this::convertToResponseDto).collect(toList());
            logger.info("Convert list of company to list of responseDto");
            logger.info("List of company response: {}", responseDtoList);
        }
        return responseDtoList;
    }

    public Company convertToEntity(CompanyRequestDto requestDto){
        Company company = null;
        if (requestDto != null){
            company = Company.builder()
                    .id(requestDto.getId())
                    .taxNumber(requestDto.getTaxNumber())
                    .phoneNumber(requestDto.getPhoneNumber())
                    .groundArea(requestDto.getGroundArea())
                    .fieldOfOperation(requestDto.getFieldOfOperation())
                    .authorizedCapital(requestDto.getAuthorizedCapital())
                    .addressInBuilding(requestDto.getAddressInBuilding())
                    .companyName(requestDto.getCompanyName())
                    .build();

            logger.info("convert request of company:{} to entity of company:{}", requestDto, company);
        }
        return company;
    }

    public Company convertResponseDtoToEntity(CompanyResponseDto responseDto){
        Company company = null;
        if (responseDto != null){
            company = Company.builder()
                    .id(responseDto.getId())
                    .taxNumber(responseDto.getTaxNumber())
                    .phoneNumber(responseDto.getPhoneNumber())
                    .groundArea(responseDto.getGroundArea())
                    .fieldOfOperation(responseDto.getFieldOfOperation())
                    .authorizedCapital(responseDto.getAuthorizedCapital())
                    .addressInBuilding(responseDto.getAddressInBuilding())
                    .companyName(responseDto.getCompanyName())
                    .build();

            logger.info("convert request of company:{} to entity of company:{}", responseDto, company);
        }
        return company;
    }


}
