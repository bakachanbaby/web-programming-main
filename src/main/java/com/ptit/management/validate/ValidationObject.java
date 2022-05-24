package com.ptit.management.validate;

import com.ptit.management.model.dto.company.CompanyRequestDto;
import com.ptit.management.model.dto.employee.EmployeeRequestDto;
import com.ptit.management.model.dto.serviceCompany.ServiceCompanyRequestDto;
import com.ptit.management.model.dto.services.ServicesRequestDto;
import com.ptit.management.model.dto.staff.StaffRequestDto;
import com.ptit.management.model.dto.staffservice.StaffServiceRequestDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationObject {

    private static final String validStringPhoneNumber = "^(\\d{3}[- .]?){2}\\d{4}$";

// trả về list gồm tất cả các lỗi nhập dữ liệu của bảng nhân viên
    public List<String> getAllErrors(StaffRequestDto requestDto){
        List<String> messagesList = new ArrayList<>();

        if (requestDto.getStaffPhoneNumber() == null){
            messagesList.add("phone number can not be null");
        }
        else if (!requestDto.getStaffPhoneNumber().matches(validStringPhoneNumber)){
            messagesList.add(
                    "phone number: " + requestDto.getStaffPhoneNumber() + " is wrong. Phone number consists of 10 characters as a number"
            );
        }

        if (requestDto.getStaffName() == null){
            messagesList.add("The staff's name can not be null");
        }

        if (requestDto.getStaffAddress() == null){
            messagesList.add("The staff's address can not be null.");
        }

        if (requestDto.getStaffCode() == null){
            messagesList.add("The staff's code can not be null");
        }

        return messagesList;
    }


    public List<String> getAllErrors(CompanyRequestDto requestDto){

        List<String> messageList = new ArrayList<>();

        if (requestDto.getCompanyName() == null){
            messageList.add("The company's name can not be null");
        }

        if (requestDto.getAddressInBuilding() == null){
            messageList.add("The company's address in the building can not be null");
        }

        if (requestDto.getAuthorizedCapital() <= 0){
            messageList.add("The company's authorized capital can not have value = 0");
        }

        if (requestDto.getFieldOfOperation() == null){
            messageList.add("The field of operation of the company can not be null");
        }

        if (requestDto.getTaxNumber() == null){
            messageList.add("The tax number of company can not be null");
        }

        if (requestDto.getGroundArea() <= 0){
            messageList.add("The ground are of company cannot be less than 0");
        }

        if (requestDto.getPhoneNumber() == null){
            messageList.add("The phone number of company can not be null");
        }
        else if (!requestDto.getPhoneNumber().matches(validStringPhoneNumber)){
            messageList.add(
                    "phone number: " + requestDto.getPhoneNumber() + " is wrong. Phone number consists of 10 characters as a number"
            );
        }

        return messageList;
    }


    public List<String> getAllErrors(EmployeeRequestDto requestDto){
        List<String> messageList = new ArrayList<>();

        if (requestDto.getEmployeeCode() == null){
            messageList.add("The employee's code can not be null");
        }

        if (requestDto.getEmployeeDob().toString() == null){
            messageList.add("The birthday of employee can not be null");
        }
        else if (!isDobValid(requestDto.getEmployeeDob().toString())){
            messageList.add(
                    "The birthday of employee: " + requestDto.getEmployeeDob().toString() + " is wrong."
                    + " The format of birthday is: yyyy-mm-dd"
            );
        }

        if (requestDto.getEmployeeIdCard() == null){
            messageList.add("The id card of employee can not be null");
        }

        if (requestDto.getEmployeeName() == null){
            messageList.add("The name of employee can not be null");
        }

        if (requestDto.getPhoneNumber() == null){
            messageList.add("The phone number of employee can not be null");
        }
        else if (!requestDto.getPhoneNumber().matches(validStringPhoneNumber)){
            messageList.add(
                    "phone number: " + requestDto.getPhoneNumber() + " is wrong. Phone number consists of 10 characters as a number"
            );
        }

        if (requestDto.getCompanyRequestDto() == null){
            messageList.add("The company can not be null");
        }

        return messageList;
    }

    public List<String> getAllErrors(StaffServiceRequestDto requestDto){
        List<String> messageList = new ArrayList<>();

        if (requestDto.getLevel() == null){
            messageList.add("The staff's level can not be null.");
        }
        if (requestDto.getPosition() == null){
            messageList.add("The staff's position can not be null.");
        }
        if (requestDto.getSalary() == 0){
            messageList.add("The staff's salary can not hava 0 value.");
        }
        if (requestDto.getServices() == null){
            messageList.add("The service can not be null.");
        }
        if (requestDto.getStaff() == null){
            messageList.add("The staff can not be null.");
        }
        return messageList;
    }

    public List<String> getAllErrors(ServicesRequestDto requestDto){
        List<String> messageList = new ArrayList<>();

        if (requestDto.getServicesCode() == null){
            messageList.add("The service's code can not be null");
        }
        if (requestDto.getServicesName() == null){
            messageList.add("The service's name can not be null");
        }
        if (requestDto.getServicesType() == null){
            messageList.add("The service's type can not be null");
        }
        if (requestDto.getServicesUnitPrice() <= 0){
            messageList.add("The service's price can not have less than 0 value");
        }
        return messageList;
    }

    public List<String> getAllErrors(ServiceCompanyRequestDto requestDto){
        List<String> messageList = new ArrayList<>();

        if (requestDto.getMonth() <= 0){
            messageList.add("The number of months using the service cannot be less than or equal to 0");
        }
        if (requestDto.getCompanyRequestDto() == null){
            messageList.add("The company cannot be null");
        }
        if (requestDto.getServiceRequestDto() == null){
            messageList.add("The service cannot be null");
        }
        return messageList;
    }

// check định dạng của ngày có đúng hay không
    private static boolean isDobValid(final String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        boolean valid = false;
        try {
            sdf.parse(date);
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return valid;
    }

}
