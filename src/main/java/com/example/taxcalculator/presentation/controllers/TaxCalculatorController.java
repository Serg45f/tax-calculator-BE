package com.example.taxcalculator.presentation.controllers;

import com.example.taxcalculator.domain.model.TaxAndSalary;
import com.example.taxcalculator.domain.services.TaxCalculationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/tax-calculator")
@Slf4j
public class TaxCalculatorController {

    private TaxCalculationService taxCalculationService;

    @GetMapping
    ResponseEntity<TaxAndSalary> getCalculatedData(@RequestParam("annualSalary") Double annualSalary) {
        log.info("Controller have been called. @RequestParam(\"annualSalary\") is {}", annualSalary);
        final String ALLOWED_ORIGIN = "http://localhost:4200";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALLOWED_ORIGIN)
                .body(taxCalculationService.getTaxAndSalary(annualSalary));
    };
}
