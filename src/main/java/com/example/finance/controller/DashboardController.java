package com.example.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finance.model.dto.DashboardDTO;
import com.example.finance.service.DashboardService;

@Controller
@RequestMapping("/api/finance/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardDTO getSummary() {
        return dashboardService.getSummary();
    }
}
