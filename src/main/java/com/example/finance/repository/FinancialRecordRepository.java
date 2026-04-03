package com.example.finance.repository;

import com.example.finance.model.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Database CRUD updated here the jpa repository makes it easier to do the database operation basically
@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    // this was just east to use AI to make the query easier from annotation and it
    // works so
    // yeah
    @Query("SELECT r FROM FinancialRecord r WHERE " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.category) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "CAST(r.date AS string) LIKE CONCAT('%', :query, '%')")
    List<FinancialRecord> searchGlobal(@Param("query") String query);
}
