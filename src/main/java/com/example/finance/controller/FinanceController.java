package com.example.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.finance.model.entity.FinancialRecord;
import com.example.finance.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/finance")
@RestController
public class FinanceController {
    @Autowired
    private FinancialService financialService;

    @GetMapping("/records")
    public List<FinancialRecord> getAllRecords() {
        return financialService.getAll();
    }

    @GetMapping("/balance")
    public Double getBalance() {
        return financialService.calculateTotalRevenue();
    }

    @PostMapping("/records")
    public FinancialRecord postMethodName(@RequestBody FinancialRecord record) {

        return financialService.save(record); // swap from list to string(data change basically)
    }

    @DeleteMapping("/records/{id}")
    public String deleteRecord(@PathVariable Long id) {
        financialService.delete(id);
        return "Record deleted of " + id;
    }
}
