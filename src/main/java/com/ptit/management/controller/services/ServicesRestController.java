package com.ptit.management.controller.services;

import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.services.ServicesResponseDto;
import com.ptit.management.model.entity.Services;
import com.ptit.management.service.services.ServicesService;
import com.ptit.management.validate.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/services")
@CrossOrigin("*")
public class ServicesRestController {

    private static final Logger logger = LoggerFactory.getLogger(ServicesRestController.class);

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ValidationObject validate;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNewServices(@RequestBody ServicesRequestDto requestDto){
        logger.info("employee is requested: {}", requestDto);

        List<String> errors = validate.getAllErrors(requestDto);
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        ServicesResponseDto ServicesAdded =servicesService.addNewServices(requestDto);
        if (ServicesAdded != null){
            logger.info("Employee added successfully!");
            return new ResponseEntity<>(ServicesAdded, HttpStatus.CREATED);
        }
        else {
            logger.error("Employee added fail!");
            return new ResponseEntity<>("Employee added fail!", HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateServices(@RequestBody ServicesRequestDto requestDto){
        logger.info("employee is requested: {}", requestDto);

        List<String> errors = validate.getAllErrors(requestDto);
        if (requestDto.getId() <=  0){
            errors.add("The id cannot be less than or equal to 0");
        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        ServicesResponseDto ServicesUpdated = servicesService.updateServices(requestDto);
        if (ServicesUpdated != null){
            logger.info("Employee updated successfully!");
            return new ResponseEntity<>(ServicesUpdated, HttpStatus.OK);
        }
        else {
            logger.error("Employee updated fail!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteServices(@PathVariable("id") int serviceId){

        if (serviceId <= 0){
            return new ResponseEntity<>("The serviceId cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Delete the employee whose employee code is {}.", serviceId);
        boolean deleteResult = servicesService.deleteServices(serviceId);
        if (deleteResult){
            logger.info("Successful employee deletion...");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            logger.error("No employee found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getServicesById(@PathVariable("id") int serviceId){

        if (serviceId <= 0){
            return new ResponseEntity<>("The serviceId cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Find employee with employee code of {}", serviceId);
        ServicesResponseDto Services = servicesService.getServicesById(serviceId);
        if (Services != null){
            logger.info("Found employee...");
            return new ResponseEntity<>(Services, HttpStatus.OK);
        }
        else {
            logger.error("No staff found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllServices(){
        logger.info("Get all employee out...");
        List<ServicesResponseDto> listOfServices = servicesService.getAllServices();
        if (!listOfServices.isEmpty()){
            logger.info("Found employee list...");
            return new ResponseEntity<>(listOfServices, HttpStatus.OK);
        }
        else {
            logger.error("No employees are saved...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
