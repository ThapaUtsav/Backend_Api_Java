package com.example.finance.controller;

import com.example.finance.repository.FinancialRecordRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.finance.model.entity.FinancialRecord;
import com.example.finance.service.FinancialService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/finance")
@RestController
public class FinanceController {
    private final FinancialRecordRepository financialRecordRepository;
    @Autowired
    private FinancialService financialService;

    FinanceController(FinancialRecordRepository financialRecordRepository) {
        this.financialRecordRepository = financialRecordRepository;
    }

    @GetMapping("/records")
    public List<FinancialRecord> getAllRecords() {
        return financialService.getAll();
    }

    @GetMapping("/balance")
    public Double getBalance() {
        return financialService.calculateTotalRevenue();
    }

    @PostMapping("/records")
    public FinancialRecord postMethodName(@Valid @RequestBody FinancialRecord record) {

        return financialService.save(record); // swap from list to string(data change basically)
    }

    @DeleteMapping("/records/{id}")
    public String deleteRecord(@PathVariable Long id) {
        financialService.delete(id);
        return "Record deleted of " + id;
    }

    @GetMapping("/summary/categories")
    public Map<String, Double> getCategorySummary() {
        return financialService.getSpendingByCategory();
    }

    @GetMapping("/search")
    public List<FinancialRecord> searchRecords(@RequestParam String query) {
        return financialRecordRepository.searchGlobal(query);
    }

}
