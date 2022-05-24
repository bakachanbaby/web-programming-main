package com.ptit.management.controller.staffservice;

import com.ptit.management.model.dto.staffservice.StaffServiceRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceResponseDto;
import com.ptit.management.service.staffservice.StaffServicesDao;
import com.ptit.management.validate.ValidationObject;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/staff_services")
@CrossOrigin("*")
public class StaffServiceController {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceController.class);

    @Autowired
    private StaffServicesDao staffServicesDao;

    @Autowired
    private ValidationObject validated;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNewStaffService(@RequestBody StaffServiceRequestDto requestDto){
        logger.info("CONTROLLER: ==> Add new staff service");

        List<String> errors = validated.getAllErrors(requestDto);
        if (requestDto.getId() == 0){
            errors.add("The id can not have 0 value.");
        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        StaffServiceResponseDto responseDto = staffServicesDao.addNewStaffService(requestDto);
        if (responseDto != null){
            logger.info("CONTROLLER: ==> add success");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
        else {
            logger.error("CONTROLLER: ==> add fail");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateStaffService(@RequestBody StaffServiceRequestDto requestDto){
        logger.info("CONTROLLER: ==> update staff service: may be change any job with staff");

        List<String> errors = validated.getAllErrors(requestDto);
        if (requestDto.getId() <= 0){
            errors.add("The id cannot be less than or equal to 0");
        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        StaffServiceResponseDto responseDto = staffServicesDao.updateStaffService(requestDto);
        if (responseDto != null){
            logger.info("CONTROLLER: ==> update success");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else {
            logger.error("CONTROLLER: ==> update fail");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){

        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("CONTROLLER: ==> delete staff service with id");
        boolean resultOfDeletion = staffServicesDao.deleteById(id);
        if (resultOfDeletion){
            logger.info("CONTROLLER: ==> delete success");
            return new ResponseEntity<>("delete success",HttpStatus.OK);
        }
        else {
            logger.error("CONTROLLER: ==> delete fail, may be the staff_service not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
