package tech.idftechnology.domas.bankmicroservice.bankmicroservice.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.MonthlyLimitService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "Endpoint which helps us to get information about monthly limits ")
@RestController
@RequestMapping(value = "/api/v2/limits")
public class MonthlyLimitRestController {

    private final MonthlyLimitService monthlyLimitService;

    @Autowired
    public MonthlyLimitRestController(MonthlyLimitService monthlyLimitService) {
        this.monthlyLimitService = monthlyLimitService;
    }

    @ApiOperation(value = "Api for getting all limits")
    @ApiResponse(code = 200, response = Transaction.class, message = "OK")
    @GetMapping
    public ResponseEntity<List<MonthlyLimit>> getAllLimits() {
        return ResponseEntity.ok(monthlyLimitService.getAllMonthlyLimits());
    }

    @ApiOperation(value = "Create a new monthly limit", notes = "This API creates a new limit and returns its dto structure.")
    @ApiResponse(code = 201, response = Long.class, message = "Limit created successfully")
    @PostMapping
    public ResponseEntity<MonthlyLimitRequestDTO> setTransactionsLimit(@Valid @RequestBody MonthlyLimitRequestDTO monthlyLimitRequestDTO){
        monthlyLimitService.setMonthlyLimit(monthlyLimitRequestDTO);
        return new ResponseEntity<>(monthlyLimitRequestDTO, HttpStatus.CREATED);
    }

}
