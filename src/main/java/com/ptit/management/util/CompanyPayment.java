package com.ptit.management.util;

import com.ptit.management.model.entity.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author: Trịnh Văn Đạt
 * creation-date: 10-12-2020
 */

@Component
public class CompanyPayment {

    private static final Logger logger = LoggerFactory.getLogger(CompanyPayment.class);
    /**
     * Công ty thuê <50 m2: 150.000.000
     * CÔng ty thuê 50<x<100: 200.000.000
     * Công ty thuê >100ms: 300.000.000
     * diện tích < 100 & số nhân viên < 10: unit-price
     * số người +5 hoặc diện tích +10 : đơn giá + 5%
     * Sô tiền dịch vụ được tính theo tỉ lệ thuận với số ngày sử dụng trong tháng.
     */

//tính tiền thuê dựa theo mặt bằng
    private double calculateGroundRent(int groundArea){
        if (groundArea < 50){
            return 150000000;
        }
        else if (groundArea < 100){
            return 200000000;
        }
        else{
            return 300000000;
        }
    }

    /**
     * Tính tiền dịch vụ.
     * < 100 m2 thì láy gia trị mặc định
     * > 100 thì tính theo %
     */
// tính tổng tiền phần trăm dịch vụ dựa vào diện tích thuê , slg nhân viên tòa nhà
    private int calculatePercentServiceRent(int groundArea, Long amountOfEmployee){
        int percentBonus = 0;

        int percentBonusGroundArea = 0;
        if (groundArea > 100){
            double extraGround = groundArea - 100;
            percentBonusGroundArea = (int) Math.ceil(extraGround / 10) * 5;
            logger.info("extra ground: {}", percentBonusGroundArea);
        }

        int percentBonusEmployee = 0;
        if (amountOfEmployee > 10){
            double extraEmployee = amountOfEmployee - 10;
            percentBonusEmployee = (int) Math.ceil(extraEmployee / 5) * 5;
            logger.info("percent ground: {}", percentBonusEmployee);
        }
        percentBonus = percentBonusEmployee + percentBonusGroundArea;
        logger.info("percent: {}", percentBonus);

        return percentBonus;
    }

// tính tổng tiền thuê (của tất cả dịch vụ)
    public double calculateTotalRent(int groundArea, Long amountOfEmployee, List<Services> services){
        double total = calculateGroundRent(groundArea);
        logger.info("fee for ground: {}", total);
        int percentBonus = calculatePercentServiceRent(groundArea, amountOfEmployee);
        logger.info("percent: {}", percentBonus);
        double multiplier = 1 + (double) percentBonus / 100;
        logger.info("multiplier: {}", multiplier);
        double extraFees = 0;
        for (Services service : services){
            extraFees += service.getServicesUnitPrice() * multiplier;
        }
        logger.info("extraFee: {}", extraFees);
        return total + extraFees;
    }


}
