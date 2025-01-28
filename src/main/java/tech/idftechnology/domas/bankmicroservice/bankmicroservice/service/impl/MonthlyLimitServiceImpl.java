package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.MonthlyLimitAlreadyExist;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper.MonthlyLimitMapper;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.MonthlyLimitRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.MonthlyLimitService;

import javax.transaction.Transactional;
import java.util.List;

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
                throw new MonthlyLimitAlreadyExist("Limit for category " + monthlyLimitRequestDTO.getCategory() + " is not exceeded yet.");
        }

        MonthlyLimit monthlyLimit = monthlyLimitMapper.mapToMonthlyLimit(monthlyLimitRequestDTO);
        limitRepository.save(monthlyLimit);
    }

}
