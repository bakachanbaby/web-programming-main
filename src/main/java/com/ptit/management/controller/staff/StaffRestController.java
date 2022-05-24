package com.ptit.management.controller.staff;

import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staff.StaffResponseDto;
import com.ptit.management.service.staff.StaffService;
import com.ptit.management.validate.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staffs")
@CrossOrigin("*")
public class StaffRestController {

    private static final Logger logger = LoggerFactory.getLogger(StaffRestController.class);

    @Autowired
    private StaffService staffService;

    @Autowired
    private ValidationObject validated;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNewStaff( @RequestBody StaffRequestDto requestDto){
        logger.info("staff is requested: {}", requestDto);

        List<String> errorList = validated.getAllErrors(requestDto);
        if (!errorList.isEmpty()){
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }

        StaffResponseDto staffAdded = staffService.addNewStaff(requestDto);
        if (staffAdded != null){
            logger.info("staff added successfully!");
            return new ResponseEntity<>(staffAdded, HttpStatus.CREATED);
        }
        else {
            logger.error("staff added fail!");
            return new ResponseEntity<>("staff added fail!", HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateStaff(@RequestBody @Validated StaffRequestDto requestDto){
        logger.info("staff is requested: {}", requestDto);

        List<String> errorList = validated.getAllErrors(requestDto);
        if (requestDto.getId() <= 0){
            errorList.add("The id cannot be less than or equal to 0");
        }
        if (!errorList.isEmpty()){
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }

        StaffResponseDto staffUpdated = staffService.updateStaff(requestDto);
        if (staffUpdated != null){
            logger.info("staff updated successfully!");
            return new ResponseEntity<>(staffUpdated, HttpStatus.OK);
        }
        else {
            logger.error("staff updated fail!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") int staffId){

        if (staffId <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Delete the staff whose staff code is {}.", staffId);
        boolean deleteResult = staffService.deleteStaff(staffId);
        if (deleteResult){
            logger.info("Successful staff deletion...");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            logger.error("No staff found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStaffById(@PathVariable("id") int staffId){

        if (staffId <= 0){
            return new ResponseEntity<>("The id cannot be less than or equal to 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Find staff with staff code of {}", staffId);
        StaffResponseDto staff = staffService.getStaffById(staffId);
        if (staff != null){
            logger.info("Found staff...");
            return new ResponseEntity<>(staff, HttpStatus.OK);
        }
        else {
            logger.error("No staff found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllStaff(){
        logger.info("Get all staff out...");
        List<StaffResponseDto> listOfstaff = staffService.getAllStaff();
        if (!listOfstaff.isEmpty()){
            logger.info("Found staff list...");
            return new ResponseEntity<>(listOfstaff, HttpStatus.OK);
        }
        else {
            logger.error("No staffs are saved...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
