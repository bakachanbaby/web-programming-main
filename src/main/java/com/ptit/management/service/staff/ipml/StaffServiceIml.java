package com.ptit.management.service.staff.ipml;

import com.ptit.management.mapper.staff.StaffMapper;
import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staff.StaffResponseDto;
import com.ptit.management.model.entity.Staff;
import com.ptit.management.repository.StaffRepository;
import com.ptit.management.service.staff.StaffService;
import com.ptit.management.service.staffservice.StaffServicesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffServiceIml implements StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceIml.class);

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private StaffServicesDao staffServicesDao;

    @Override
    @Transactional
    public StaffResponseDto addNewStaff(StaffRequestDto requestDto) {
        logger.info("starting add staff...");
        Staff staff = staffMapper.convertToEntity(requestDto);
        Staff staffAdded = staffRepository.save(staff);
        logger.info("staff is added: {}", staffAdded);
        logger.info("adding staff finish...");
        return staffMapper.convertToResponseDto(staffAdded);
    }

    @Override
    @Transactional
    public StaffResponseDto updateStaff(StaffRequestDto requestDto) {
        logger.info("starting updating staff...");
        Staff staffEntity = staffMapper.convertToEntity(requestDto);
        Staff staff = staffRepository.findById(staffEntity.getId()).orElse(null);
        Staff staffUpdated = null;
        if (staff != null){
            staffUpdated = staffRepository.saveAndFlush(staffEntity);
            logger.info("staff is updated: {}", staffUpdated);
        }
        else {
            logger.error("staff not found...");
        }
        logger.info("updating staff finish...");
        return staffMapper.convertToResponseDto(staffUpdated);
    }

    @Override
    @Transactional
    public boolean deleteStaff(int staffId) {
        logger.info("starting delete staff...");
        boolean resultDeleted = false;
        Staff staff = staffRepository.findById(staffId).orElse(null);
        if (staff != null){
            logger.info("delete all service of staff before delete staff");
            staffServicesDao.deleteByStaff(staff);
            logger.info("finish delete all staff-service of staff");
            logger.info("delete staff: {}", staff);
            staffRepository.deleteById(staffId);
            resultDeleted = true;
        }
        return resultDeleted;
    }

    @Override
    public List<StaffResponseDto> getAllStaff() {
        logger.info("starting read list of staff...");
        List<Staff> staffList = staffRepository.findAll();
        logger.info("List of staffs are read: {}", staffList);
        logger.info("reading list of staff finish...");
        return staffMapper.convertToResponseDtoList(staffList);
    }

    @Override
    public StaffResponseDto getStaffById(int staffId) {
        logger.info("starting read staff...");
        Staff staff = staffRepository.findById(staffId).orElse(null);
        logger.info("staff is read: {}", staff);
        logger.info("reading staff finish...");
        return staffMapper.convertToResponseDto(staff);
    }
}
