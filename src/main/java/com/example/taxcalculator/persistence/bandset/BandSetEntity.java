package com.example.taxcalculator.persistence.bandset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "band_set")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class BandSetEntity {
   @Id
   Long id;
   @Column(name = "rate_number")
   Long taxBandId;
   @Column(name = "timestamp")
   Timestamp timestamp;
}
