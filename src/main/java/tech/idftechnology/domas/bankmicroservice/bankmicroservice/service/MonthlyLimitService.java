package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service;

import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;

import java.util.List;

public interface MonthlyLimitService {

    List<MonthlyLimit> getAllMonthlyLimits();

    void setMonthlyLimit(MonthlyLimitRequestDTO monthlyLimitRequestDTO);

}
