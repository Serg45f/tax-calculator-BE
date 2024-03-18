package com.example.taxcalculator.domain.providers;

import com.example.taxcalculator.domain.model.TaxBand;
import com.example.taxcalculator.persistence.taxband.TaxBandDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;


@Slf4j
@Component
@AllArgsConstructor
//@RequiredArgsConstructor
public class TaxCalculationProvider {

//    private TaxBandRepository taxBandRepository;
    private TaxBandDAO taxBandRepository;

    public BigDecimal grossMonthlySalaryCalculation(BigDecimal grossSalary) {
        return grossSalary.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal annualTaxPaidCalculation(BigDecimal grossSalary, Integer rateNumber) {
        BigDecimal totalTax = new BigDecimal(0);
        List<TaxBand> bands = taxBandRepository.findByRateNumber(rateNumber).stream()
                .sorted(Comparator.comparingInt(TaxBand::getLowerLimit)).toList();

        for (TaxBand band : bands) {
            if (grossSalary.doubleValue() > band.getLowerLimit().doubleValue()) {
                if (band.getUpperLimit() != null) {
                    if (grossSalary.doubleValue() < band.getUpperLimit().doubleValue()) {
                        totalTax = totalTax.add(
                                grossSalary
                                        .subtract(BigDecimal.valueOf(band.getLowerLimit()))
                                        .multiply(BigDecimal.valueOf(((double) band.getTaxRateValue()) / 100)));
                    } else {
                        totalTax = totalTax.add(
                                BigDecimal.valueOf((band.getUpperLimit() - band.getLowerLimit()) * ((double) band.getTaxRateValue()) / 100));
                    }
                } else {
                    totalTax = totalTax.add(
                            grossSalary
                                    .subtract(BigDecimal.valueOf(band.getLowerLimit()))
                                    .multiply(BigDecimal.valueOf(((double) band.getTaxRateValue()) / 100)));
                }
            }
        }
        log.info("annualTaxPaidCalculation, totalTax: {}",totalTax);
        return totalTax;
    }

    public BigDecimal monthlyTaxPaidCalculation(BigDecimal annualTaxPaid) {
        return annualTaxPaid.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal netAnnualSalaryCalculation(BigDecimal grossAnnualSalary, BigDecimal annualTaxPaid) {
        return grossAnnualSalary.subtract(annualTaxPaid);
    }

    public BigDecimal netMonthlySalaryCalculation(BigDecimal grossAnnualSalary, BigDecimal annualTaxPaid) {
        return (grossAnnualSalary.subtract(annualTaxPaid)).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
    }
}
