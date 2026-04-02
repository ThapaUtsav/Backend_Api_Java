package com.example.finance.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "financial_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String category;

    private LocalDate date;
    private String description;
}
