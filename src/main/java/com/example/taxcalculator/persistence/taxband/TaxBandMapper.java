package com.example.taxcalculator.persistence.taxband;

import com.example.taxcalculator.config.mappers.MapperCommonConfigs;
import com.example.taxcalculator.domain.model.TaxBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCommonConfigs.class)
public interface TaxBandMapper {

    @Mapping(target = ".", source = "id", ignore = true)
    TaxBand to(TaxBandEntity taxBandEntity);
}
