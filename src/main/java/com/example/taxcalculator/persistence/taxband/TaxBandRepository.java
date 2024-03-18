package com.example.taxcalculator.persistence.taxband;

import com.example.taxcalculator.domain.model.TaxBand;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TaxBandRepository {
    List<TaxBand> findByRateNumber(Integer rateName);
}
