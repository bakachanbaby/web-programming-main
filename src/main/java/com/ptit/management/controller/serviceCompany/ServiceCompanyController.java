package com.ptit.management.controller.serviceCompany;

import com.ptit.management.model.dto.serviceCompany.ServiceCompanyRequestDto;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyResponseDto;
import com.ptit.management.service.serviceCompany.ServiceCompanyService;
import com.ptit.management.validate.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company/services")
@CrossOrigin("*")
public class ServiceCompanyController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCompanyController.class);

    @Autowired
    private ServiceCompanyService service;

    @Autowired
    private ValidationObject validate;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getByCompanyId(@PathVariable int id){
        logger.info("Get all service use by company {}", id);
        List<ServiceCompanyResponseDto> responseDto = service.getAllServiceCompany(id);
        if(responseDto != null && !responseDto.isEmpty()){
            logger.info("Success");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else {
            logger.info("Failed :((((((");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addServiceCompany(@RequestBody ServiceCompanyRequestDto requestDto){

        List<String> errors = validate.getAllErrors(requestDto);
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        logger.info("Add new service {} into company {}", requestDto, requestDto.getCompanyRequestDto());

        ServiceCompanyResponseDto responseDto = service.addServiceToCompany(requestDto);
        if(responseDto != null){
            logger.info("Success");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
        else{
            logger.info("Add Failed");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateService(@RequestBody ServiceCompanyRequestDto requestDto){

        List<String> errors = validate.getAllErrors(requestDto);
        if (requestDto.getId() <= 0){
            errors.add("The id cannot be less than or equal to 0");
        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        logger.info("update service {}", requestDto);
        ServiceCompanyResponseDto responseDto = service.updateServiceCompany(requestDto);
        if(responseDto != null){
            logger.info("Success");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else{
            logger.info("Update Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteService(@PathVariable int id){

        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Delete service with id {}", id);
        boolean response = service.deleteServiceCompany(id);
        if(response){
            logger.info("Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            logger.info("Delete Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
