package com.ptit.management.service.staffservice.iml;

import com.google.common.util.concurrent.ServiceManager;
import com.ptit.management.mapper.services.ServicesMapper;
import com.ptit.management.mapper.staff.StaffMapper;
import com.ptit.management.mapper.staffservice.StaffServiceMapper;
import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceResponseDto;
import com.ptit.management.model.entity.Services;
import com.ptit.management.model.entity.Staff;
import com.ptit.management.model.entity.StaffServices;
import com.ptit.management.repository.StaffServiceRepository;
import com.ptit.management.service.staffservice.StaffServicesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceDaoIml implements StaffServicesDao {

    private static final Logger logger = LoggerFactory.getLogger(StaffServicesDao.class);

    @Autowired
    private StaffServiceRepository staffServiceRepository;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private StaffServiceMapper staffServiceMapper;


    @Override
    public StaffServiceResponseDto addNewStaffService(StaffServiceRequestDto requestDto) {
        logger.info("add new staff_service ...");
        logger.info("request: {}", requestDto);
        StaffServices staffServices = staffServiceMapper.convertToEntity(requestDto);
        StaffServices staffServicesAdded = staffServiceRepository.save(staffServices);
        logger.info("staff_service was added: {}", staffServicesAdded);
        logger.info("finish ....");
        return staffServiceMapper.convertToResponseDto(staffServicesAdded);
    }

    @Override
    public StaffServiceResponseDto updateStaffService(StaffServiceRequestDto requestDto) {
        logger.info("updating staff_service ....");
        logger.info("Request: {}", requestDto);
        StaffServices staffServices = staffServiceMapper.convertToEntity(requestDto);
        StaffServices staffServicesIsExisted = staffServiceRepository.findById(staffServices.getId()).orElse(null);
        if (staffServicesIsExisted != null){
            StaffServices staffServicesUpdated = staffServiceRepository.saveAndFlush(staffServices);
            logger.info("updating into database success ....");
            return staffServiceMapper.convertToResponseDto(staffServicesUpdated);
        }
        else {
            logger.error("finish ....");
            return null;
        }
    }

    @Override
    public List<StaffServiceResponseDto> getServicesByStaff(StaffRequestDto staffRequestDto) {
        logger.info("search all staff_service have a staff: {}", staffRequestDto);
        Staff staff = staffMapper.convertToEntity(staffRequestDto);
        List<StaffServices> staffServicesListIsRead = staffServiceRepository.findByStaff(staff);
        logger.info("finish ....");
        return staffServiceMapper.convertToResponseDtoList(staffServicesListIsRead);
    }

    @Override
    public List<StaffServiceResponseDto> getStaffByService(ServicesRequestDto servicesRequestDto) {
        logger.info("search all staff_service have a service:{}", servicesRequestDto);
        Services services = servicesMapper.convertToEntity(servicesRequestDto);
        List<StaffServices> staffServicesListIsRead = staffServiceRepository.findByServices(services);
        logger.info("finish ....");
        return staffServiceMapper.convertToResponseDtoList(staffServicesListIsRead);
    }

    @Override
    public boolean deleteById(int staffServiceId) {
        boolean resultOfDelete = false;
        logger.info("delete staff_service ....");
        StaffServices staffServicesIsExisted = staffServiceRepository.findById(staffServiceId).orElse(null);
        if (staffServicesIsExisted != null){
            staffServiceRepository.deleteById(staffServiceId);
            logger.info("delete into database success");
            resultOfDelete = true;
        }
        else {
            logger.error("not found staff_service");
        }
        logger.info("finish ....");
        return resultOfDelete;
    }

    @Override
    public void deleteByStaff(Staff staff) {
        logger.info("delete staff_service with staff: {}", staff);
        List<StaffServices> staffServicesListIsExistedWithStaff = staffServiceRepository.findByStaff(staff);
        if (!staffServicesListIsExistedWithStaff.isEmpty()){
            staffServiceRepository.deleteByStaff(staff);
            logger.info("delete success");
        }
        else {
            logger.warn("This employee has not participated in any service yet.");
        }
    }

    @Override
    public void deleteByService(Services services) {
        logger.info("delete staff_service with service: {}", services);
        List<StaffServices> staffServicesListIsExistedWithStaff = staffServiceRepository.findByServices(services);
        if (!staffServicesListIsExistedWithStaff.isEmpty()){
            staffServiceRepository.deleteByServices(services);
            logger.info("delete success");
        }
        else {
            logger.warn("This service has not participated in any service yet.");
        }
    }
}
