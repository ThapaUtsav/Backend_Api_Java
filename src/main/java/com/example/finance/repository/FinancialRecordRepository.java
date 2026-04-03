package com.example.finance.repository;

import com.example.finance.model.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//Database CRUD updated here the jpa repository makes it easier to do the database operation basically
@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByDescriptionContainingIgnoreCase(String description);
}
