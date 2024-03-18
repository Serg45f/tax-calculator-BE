package com.example.taxcalculator.domain.mappers;

import com.example.taxcalculator.config.mappers.MapperCommonConfigs;
import com.example.taxcalculator.domain.model.TaxAndSalary;
import com.example.taxcalculator.domain.providers.TaxCalculationProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(config = MapperCommonConfigs.class)
public abstract class TaxCalculationMapper {
    TaxCalculationProvider taxCalculationProvider;
    @Mapping(
            target = "grossAnnualSalary",
            expression = "java(highlightProvider.startDateHighlight(sow))")
    @Mapping(
            target = "endDateHighlight",
            expression = "java(highlightProvider.endDateHighlight(sow))")
    @Mapping(
            target = "leftoverHighlight",
            expression = "java(highlightProvider.leftoverHighlight(sow))")
    @Mapping(
            target = "leftoverPercentageHighlight",
            expression = "java(highlightProvider.leftoverHighlight(sow))")
    public abstract TaxAndSalary toDto(BigDecimal grossSalary);
}
