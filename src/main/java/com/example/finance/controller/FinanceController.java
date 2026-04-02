package com.example.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.finance.model.entity.FinancialRecord;
import com.example.finance.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("api/finance")
@RestController
public class FinanceController {
    @Autowired
    private FinancialService financialService;

    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return financialService.getAll();
    }

    @PostMapping
    public String postMethodName(@RequestBody FinancialRecord record) {

        return financialService.getAll().toString(); // swap from list to string(data change basically)
    }

}
