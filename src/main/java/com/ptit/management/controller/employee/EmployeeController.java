package com.ptit.management.controller.employee;


import com.ptit.management.model.dto.employee.EmployeeRequestDto;
import com.ptit.management.model.dto.employee.EmployeeResponseDto;
import com.ptit.management.service.employee.EmployeeService;
import com.ptit.management.validate.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/company/employees")
@CrossOrigin("*")
@Transactional
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ValidationObject validated;

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable("id") int id){


        logger.info("find Employee of company with id: {}", id);
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        EmployeeResponseDto employeeCompanyResponseDto = employeeService.getEmployeeById(id);
        if (employeeCompanyResponseDto != null){
            logger.info("successfully ...");
            return new ResponseEntity<>(employeeCompanyResponseDto, HttpStatus.OK);
        }
        else {
            logger.error("employee of company not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
        logger.info("get all employee");
        List<EmployeeResponseDto> employeeCompanyResponseDtoList = employeeService.getAll();
        if (employeeCompanyResponseDtoList.isEmpty()){
            logger.error("not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            logger.info("successfully ...");
            return new ResponseEntity<>(employeeCompanyResponseDtoList, HttpStatus.OK);
        }
    }

    @Transactional
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addEmployeeCompany(@RequestBody EmployeeRequestDto employee){
        logger.info("add employee of company: {}", employee);

        List<String> errors = validated.getAllErrors(employee);
        if (!errors.isEmpty()){
            logger.error("BAD_REQUEST: maybe missing any field of ");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        EmployeeResponseDto employeeCompanyResponseDto = employeeService.addNewEmployee(employee);
        if (employeeCompanyResponseDto != null){
            logger.info("successfully");
            return new ResponseEntity<>(employeeCompanyResponseDto, HttpStatus.CREATED);
        }
        else {
            logger.error("fail to add new employee of company");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @Transactional
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateEmployeeCompany(@RequestBody EmployeeRequestDto employee){
        logger.error("update employee of company...");

        List<String> errors = validated.getAllErrors(employee);
        if (employee.getId() <= 0){
            errors.add("The id cannot be less than or equal to 0");
        }
        if (!errors.isEmpty()){
            logger.error("BAD_REQUEST: maybe missing any field of ");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        EmployeeResponseDto employeeCompanyResponseDto = employeeService.updateEmployee(employee);
        if (employeeCompanyResponseDto != null){
            logger.info("successfully...");
            return new ResponseEntity<>(employeeCompanyResponseDto, HttpStatus.OK);
        }
        else {
            logger.error("fail to update employee of company");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){

        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("delete employee of company with id:{}", id);
        boolean employeeCompanyResponseDto = employeeService.deleteEmployeeById(id);
        if (employeeCompanyResponseDto){
            logger.info("successfully");
            return new ResponseEntity<>(employeeCompanyResponseDto, HttpStatus.OK);
        }
        else {
            logger.info("delete fail");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get_by_company", produces = "application/json")
    public ResponseEntity<?> getListEmployeeByCompanyId(@RequestParam(name = "company_id") int companyId){

        if (companyId <= 0){
            return new ResponseEntity<>("The companyId cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        logger.info("search employee by company");
        List<EmployeeResponseDto> responseDtoList = employeeService.getListEmployeeByCompanyId(companyId);
        logger.info("response: {}", responseDtoList);
        if (responseDtoList.isEmpty()){
            logger.error("empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(responseDtoList , HttpStatus.OK);
        }
    }

}
