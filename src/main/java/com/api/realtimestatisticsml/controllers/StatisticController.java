package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.repository.TransactionsCache;
import com.api.realtimestatisticsml.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(@Autowired StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistics")
    public ResponseEntity statistics() {
        return ResponseEntity.ok(this.statisticService.getStatistic());
    }

}
