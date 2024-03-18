package com.example.taxcalculator.persistence.bandset;

import com.example.taxcalculator.config.mappers.MapperCommonConfigs;
import com.example.taxcalculator.domain.model.BandSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCommonConfigs.class)
public interface BandSetMapper {

    @Mapping(target = ".", source = "id", ignore = true)
    BandSet to(BandSetEntity bandSetEntity);
}
