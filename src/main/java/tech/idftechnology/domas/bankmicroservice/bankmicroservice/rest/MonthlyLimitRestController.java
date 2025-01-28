package tech.idftechnology.domas.bankmicroservice.bankmicroservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.MonthlyLimitService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/limits")
public class MonthlyLimitRestController {

    private final MonthlyLimitService monthlyLimitService;

    @Autowired
    public MonthlyLimitRestController(MonthlyLimitService monthlyLimitService) {
        this.monthlyLimitService = monthlyLimitService;
    }

    @GetMapping
    public ResponseEntity<List<MonthlyLimit>> getAllLimits() {
        return ResponseEntity.ok(monthlyLimitService.getAllMonthlyLimits());
    }

    @PostMapping
    public ResponseEntity<MonthlyLimitRequestDTO> setTransactionsLimit(@Valid @RequestBody MonthlyLimitRequestDTO monthlyLimitRequestDTO){
        monthlyLimitService.setMonthlyLimit(monthlyLimitRequestDTO);
        return new ResponseEntity<>(monthlyLimitRequestDTO, HttpStatus.CREATED);
    }


}
