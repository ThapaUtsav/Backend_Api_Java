package com.example.finance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.model.entity.FinancialRecord;

import com.example.finance.repository.FinancialRecordRepository;

import lombok.NonNull;

@Service
public class FinancialService {

    @Autowired
    private FinancialRecordRepository repository;

    public List<FinancialRecord> getAll() {
        return repository.findAll();
    }

    public FinancialRecord save(@NonNull FinancialRecord record) {
        return repository.save(record);
    }

    public Double calculateTotalRevenue() {
        return repository.findAll().stream()
                .mapToDouble(record -> {
                    if ("INCOME".equalsIgnoreCase(record.getType())) {
                        return record.getAmount().doubleValue();// GET THE TOTAL REVENUE?
                    } else {
                        return -record.getAmount().doubleValue(); // or the subtracted reveneu basically epxense is
                                                                  // subtracted
                    }
                })
                .sum();
    }

    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }
}