package com.example.finance.repository;

import com.example.finance.model.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

}
