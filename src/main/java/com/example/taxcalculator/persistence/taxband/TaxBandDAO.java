package com.example.taxcalculator.persistence.taxband;

import com.example.taxcalculator.domain.model.TaxBand;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxBandDAO extends JpaRepository<TaxBand, Long> {
    @Bean
    List<TaxBand> findByRateNumber(Integer rateNumber);
}
