package com.example.taxcalculator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@AllArgsConstructor
//@Builder
//public class TaxBand {
//    private Long id;
//    private String taxBand;
//    private Integer lowerLimit;
//    private Integer upperLimit;
//    private Integer taxRateValue;
//    private Long rateNumber;
//}

@Entity
@Table(name = "tax_band")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class TaxBand {
    @JsonIgnore
    @Id
    @Column(columnDefinition = "id", updatable = false, name = "id")
    Long id;
    @Column(name = "name")
    String taxBand;
    @Column(name = "lower_limit")
    @NotEmpty
    Integer lowerLimit;
    @Column(name = "upper_limit")
    Integer upperLimit;
    @Column(name = "tax_rate_value")
    @NotEmpty
    Integer taxRateValue;
    @Column(name = "rate_number")
    @NotEmpty
    Long rateNumber;

}
