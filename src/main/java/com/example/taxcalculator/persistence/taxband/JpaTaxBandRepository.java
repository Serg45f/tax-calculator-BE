package com.example.taxcalculator.persistence.taxband;

import com.example.taxcalculator.domain.model.TaxBand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Repository
public class JpaTaxBandRepository implements TaxBandRepository {

    private TaxBandDAO taxBandDAO;

//    private TaxBandMapper taxBandMapper;

    @Override
    public List<TaxBand> findByRateNumber(Integer rateNumber) {
//        List<TaxBandEntity> bands = taxBandDAO.findByRateNumber(rateNumber);
//        return bands.stream().map(band -> taxBandMapper.to(band)).toList();
        return taxBandDAO.findByRateNumber(rateNumber);
    }
}
