package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;
import com.nsa.team10.asgproject.services.interfaces.IMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricsApiController
{
    private final IMetricsService metricsService;

    @Autowired
    public MetricsApiController(IMetricsService metricsService)
    {
        this.metricsService = metricsService;
    }

    @GetMapping("/stagenumbers")
    public List<StageMetricsDao> findStageMetrics()
    {
        return metricsService.findStageMetrics();
    }

}
