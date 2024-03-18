package com.example.taxcalculator.domain.services;

import com.example.taxcalculator.domain.model.TaxAndSalary;
import com.example.taxcalculator.domain.providers.TaxCalculationProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class TaxCalculationService {

    public static final Integer RATE_NUMBER = 1;
    private TaxCalculationProvider calculationProvider;

    public TaxAndSalary getTaxAndSalary(Double grossAnnualSalary) {
        BigDecimal grossSalary = BigDecimal.valueOf(grossAnnualSalary);
        BigDecimal annualTax = calculationProvider.annualTaxPaidCalculation(grossSalary, RATE_NUMBER);
        log.info("TaxCalculationService was called");
        return TaxAndSalary.builder()
                .grossAnnualSalary(grossSalary)
                .grossMonthlySalary(calculationProvider.grossMonthlySalaryCalculation(grossSalary))
                .netAnnualSalary(calculationProvider.netAnnualSalaryCalculation(grossSalary, annualTax))
                .netMonthlySalary(calculationProvider.netMonthlySalaryCalculation(grossSalary, annualTax))
                .annualTaxPaid(annualTax)
                .monthlyTaxPaid(calculationProvider.monthlyTaxPaidCalculation(annualTax))
                .build();
    }
}
