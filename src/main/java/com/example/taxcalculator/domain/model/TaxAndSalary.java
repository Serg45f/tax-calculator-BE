package com.example.taxcalculator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxAndSalary {
    private BigDecimal grossAnnualSalary;
    private BigDecimal grossMonthlySalary;
    private BigDecimal netAnnualSalary;
    private BigDecimal netMonthlySalary;
    private BigDecimal annualTaxPaid;
    private BigDecimal monthlyTaxPaid;
}
