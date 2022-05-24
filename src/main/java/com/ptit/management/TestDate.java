package com.ptit.management;

import com.ptit.management.util.CompanyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class TestDate {

    public static void main(String[] args) {
        double total = 0;
        int percentBonus = 0;
        int groundArea = 100;
        int amountOfEmployee = 10;

//        double extraGround = groundArea - 100;
//        double extraEmployee = amountOfEmployee - 10;
//        int percentBonusGroundArea = (int) Math.ceil(extraGround / 10);
//        int percentBonusEmployee = (int) Math.ceil(extraEmployee / 5);
//        percentBonus = (percentBonusEmployee + percentBonusGroundArea) * 10;
//
//        if (percentBonus < 0){
//            percentBonus = 0;
//        }

        System.out.println(percentBonus);
    }

}
