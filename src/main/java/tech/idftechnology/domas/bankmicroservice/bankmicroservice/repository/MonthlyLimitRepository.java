package tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonthlyLimitRepository extends JpaRepository<MonthlyLimit, UUID> {

    Optional<MonthlyLimit> findByLimitSumGreaterThanAndCategory(BigDecimal sum, String category);

    List<MonthlyLimit> findMonthlyLimitsByCategory(String category);

}
