package com.example.finance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.model.entity.FinancialRecord;

// 2. Import the Repository you actually created
import com.example.finance.repository.FinancialRecordRepository;

@Service
public class FinancialService {

    @Autowired
    // 3. Match the variable type to the Import above
    private FinancialRecordRepository repository;

    public List<FinancialRecord> getAll() {
        return repository.findAll();
    }

    public FinancialRecord save(FinancialRecord record) {
        return repository.save(record);
    }
}