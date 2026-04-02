package com.example.finance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.model.entity.FinancialRecord;

import com.example.finance.repository.FinancialRecordRepository;

@Service
public class FinancialService {

    @Autowired
    private FinancialRecordRepository repository;

    public List<FinancialRecord> getAll() {
        return repository.findAll();
    }

    public FinancialRecord save(FinancialRecord record) {
        return repository.save(record);
    }

    public Double calculateTotalRevenue() {
        return repository.findAll().stream()
                .mapToDouble(record -> {
                    if ("INCOME".equalsIgnoreCase(record.getType())) {
                        return record.getAmount().doubleValue();
                    } else {
                        return -record.getAmount().doubleValue();
                    }
                })
                .sum();
    }
}