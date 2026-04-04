package com.example.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.finance.model.dto.DashboardDTO;
import com.example.finance.model.entity.FinancialRecord;
import com.example.finance.repository.FinancialRecordRepository;

@Service
public class DashboardService {
    private final FinancialRecordRepository repository;

    public DashboardService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    public DashboardDTO getSummary() {
        List<FinancialRecord> records = repository.findAll();

        // Calculate Total Income
        BigDecimal income = records.stream()
                .filter(r -> "INCOME".equalsIgnoreCase(r.getType()))
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate Total Expenses
        BigDecimal expenses = records.stream()
                .filter(r -> "EXPENSE".equalsIgnoreCase(r.getType()))
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Group by Category
        Map<String, BigDecimal> categoryMap = records.stream()
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.mapping(FinancialRecord::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return DashboardDTO.builder()
                .totalIncome(income)
                .totalExpenses(expenses)
                .netBalance(income.subtract(expenses))
                .categoryBreakdown(categoryMap)
                .build();
    }
}
