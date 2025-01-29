package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.MonthlyLimitAlreadyExistException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper.MonthlyLimitMapper;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.MonthlyLimitRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.MonthlyLimitService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MonthlyLimitServiceImpl implements MonthlyLimitService {

    private final MonthlyLimitRepository limitRepository;

    private final MonthlyLimitMapper monthlyLimitMapper;

    @Override
    public List<MonthlyLimit> getAllMonthlyLimits() {
        return limitRepository.findAll();
    }

    @Transactional
    @Override
    public void setMonthlyLimit(MonthlyLimitRequestDTO monthlyLimitRequestDTO) {
        List<MonthlyLimit> existingLimitsByCategory = limitRepository.findMonthlyLimitsByCategory(monthlyLimitRequestDTO.getCategory());

        for (MonthlyLimit limit: existingLimitsByCategory){
            if (limit.getLimitSum().doubleValue() > 0.0)
                throw new MonthlyLimitAlreadyExistException("Limit for category " + monthlyLimitRequestDTO.getCategory() + " is not exceeded yet.");
        }

        MonthlyLimit monthlyLimit = monthlyLimitMapper.mapToMonthlyLimit(monthlyLimitRequestDTO);
        limitRepository.save(monthlyLimit);
    }

    @Scheduled(cron = "0 0 0 1 * *")
    @Transactional
    public void resetMonthlyLimitByCategory() {
        limitRepository.deleteAll();
        MonthlyLimit monthlyCategoryGoodsLimit = new MonthlyLimit(new BigDecimal("1000"), new BigDecimal("1000"), OffsetDateTime.now(ZoneOffset.ofHours(3)), "USD", "goods");
        MonthlyLimit monthlyCategoryServicesLimit = new MonthlyLimit(new BigDecimal("1000"), new BigDecimal("1000"), OffsetDateTime.now(ZoneOffset.ofHours(3)), "USD", "services");
        limitRepository.save(monthlyCategoryGoodsLimit);
        limitRepository.save(monthlyCategoryServicesLimit);
    }

}
