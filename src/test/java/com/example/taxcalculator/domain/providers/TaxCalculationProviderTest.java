package com.example.taxcalculator.domain.providers;

import com.example.taxcalculator.domain.model.TaxBand;
import com.example.taxcalculator.persistence.taxband.TaxBandDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.example.taxcalculator.domain.services.TaxCalculationService.RATE_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxCalculationProviderTest {


    @Mock
    private TaxBandDAO taxBandDAORepository;

    @InjectMocks
    TaxCalculationProvider testee;

    @Test
    void grossMonthlySalaryCalculation() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(5000);
        BigDecimal expected = new BigDecimal(5000).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);

        // when
        BigDecimal result = testee.grossMonthlySalaryCalculation(grossAnnualSalary);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void given_annualTaxPaidCalculation_When_AnnualGrossSalaryLessThenTaxLowerLimit_Then_TotalTaxShouldBeZero() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(1000);
        List<TaxBand> bands = List.of(
                TaxBand.builder().lowerLimit(0).upperLimit(1000).taxRateValue(0).build(),
                TaxBand.builder().lowerLimit(1000).upperLimit(2000).taxRateValue(10).build(),
                TaxBand.builder().lowerLimit(2000).upperLimit(null).taxRateValue(20).build()
        );

        BigDecimal expected = new BigDecimal(0);

        // when
        BigDecimal result = testee.annualTaxPaidCalculation(grossAnnualSalary, RATE_NUMBER);

        // then
        verify(taxBandDAORepository).findByRateNumber(RATE_NUMBER);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void given_annualTaxPaidCalculation_When_AnnualGrossSalaryLessThenTaxHigherAndMoreThenLowerLimit_Then_TotalTaxShouldNotBeZero() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(1100);
        List<TaxBand> bands = List.of(
                TaxBand.builder().lowerLimit(0).upperLimit(1000).taxRateValue(0).build(),
                TaxBand.builder().lowerLimit(1000).upperLimit(2000).taxRateValue(10).build(),
                TaxBand.builder().lowerLimit(2000).upperLimit(null).taxRateValue(20).build()
        );
        BigDecimal expected = BigDecimal.valueOf(10.0);

        // when
        when(taxBandDAORepository.findByRateNumber(RATE_NUMBER)).thenReturn(bands);

        BigDecimal result = testee.annualTaxPaidCalculation(grossAnnualSalary, RATE_NUMBER);

        // then
        verify(taxBandDAORepository).findByRateNumber(RATE_NUMBER);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void given_annualTaxPaidCalculation_When_AnnualGrossSalaryMoreThenLastUpperLimit_Then_TotalTaxShouldNotBeZero() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(5000);
        List<TaxBand> bands = List.of(
                TaxBand.builder().lowerLimit(0).upperLimit(1000).taxRateValue(0).build(),
                TaxBand.builder().lowerLimit(1000).upperLimit(2000).taxRateValue(10).build(),
                TaxBand.builder().lowerLimit(2000).upperLimit(null).taxRateValue(20).build()
        );
        BigDecimal expected = BigDecimal.valueOf(100.0 + 600.0);

        // when
        when(taxBandDAORepository.findByRateNumber(RATE_NUMBER)).thenReturn(bands);

        BigDecimal result = testee.annualTaxPaidCalculation(grossAnnualSalary, RATE_NUMBER);

        // then
        verify(taxBandDAORepository).findByRateNumber(RATE_NUMBER);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void monthlyTaxPaidCalculation() {
        // given
        BigDecimal grossAnnualTaxPaid = new BigDecimal(1000);
        BigDecimal expected = grossAnnualTaxPaid.divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);;

        // when
        BigDecimal result = testee.monthlyTaxPaidCalculation(grossAnnualTaxPaid);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void netAnnualSalaryCalculation() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(5000);
        BigDecimal grossAnnualTaxPaid = new BigDecimal(1000);
        BigDecimal expected = new BigDecimal(4000);

        // when
        BigDecimal result = testee.netAnnualSalaryCalculation(grossAnnualSalary, grossAnnualTaxPaid);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void netMonthlySalaryCalculation() {
        // given
        BigDecimal grossAnnualSalary = new BigDecimal(5000);
        BigDecimal grossAnnualTaxPaid = new BigDecimal(1000);
        BigDecimal expected = new BigDecimal(4000).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);

        // when
        BigDecimal result = testee.netMonthlySalaryCalculation(grossAnnualSalary, grossAnnualTaxPaid);

        // then
        assertThat(result).isEqualTo(expected);
    }
}