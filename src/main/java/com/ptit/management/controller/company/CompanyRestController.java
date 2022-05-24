package com.ptit.management.controller.company;

import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.company.CompanyResponseDto;
import com.ptit.management.service.company.CompanyService;
import com.ptit.management.validate.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@CrossOrigin("*")
public class CompanyRestController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidationObject validated;

    @Transactional
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNewCompany(@RequestBody CompanyRequestDto requestDto){
        logger.info("company is requested: {}", requestDto);

        List<String> errors = validated.getAllErrors(requestDto);
        if (!errors.isEmpty()){
            logger.error("BAD_REQUEST: maybe missing any field of company");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        CompanyResponseDto CompanyAdded = companyService.addNewCompany(requestDto);
        if (CompanyAdded != null){
            logger.info("Company added successfully!");
            return new ResponseEntity<>(CompanyAdded, HttpStatus.CREATED);
        }
        else {
            logger.error("Company added fail!");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @Transactional
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyRequestDto requestDto){
        logger.info("company is requested: {}", requestDto);

        List<String> errors = validated.getAllErrors(requestDto);
        if (requestDto.getId() <= 0){
            errors.add("The id cannot be less than or equal to 0");
        }
        if (!errors.isEmpty()){
            logger.error("BAD_REQUEST: maybe missing any field of company");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        CompanyResponseDto CompanyUpdated = companyService.updateCompany(requestDto);
        if (CompanyUpdated != null){
            logger.info("Company updated successfully!");
            return new ResponseEntity<>(CompanyUpdated, HttpStatus.OK);
        }
        else {
            logger.error("Company updated fail!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int companyId){

        if (companyId <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Delete the company whose company code is {}.", companyId);
        boolean deleteResult = companyService.deleteCompany(companyId);
        if (deleteResult){
            logger.info("Successful company deletion...");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            logger.error("No company found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getCompanyByIdById(@PathVariable("id") int companyId){

        if (companyId <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Find company with company code of {}", companyId);
        CompanyResponseDto company = companyService.getCompanyById(companyId);
        if (company != null){
            logger.info("Found company...");
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        else {
            logger.error("No staff found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCompany(){
        logger.info("Get all company out...");
        List<CompanyResponseDto> listOfCompany = companyService.getAllCompany();
        if (!listOfCompany.isEmpty()){
            logger.info("Found companys list...");
            return new ResponseEntity<>(listOfCompany, HttpStatus.OK);
        }
        else {
            logger.error("No companys are saved...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
