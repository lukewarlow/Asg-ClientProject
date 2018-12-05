package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;
import com.nsa.team10.asgproject.services.interfaces.IMetricsService;
import com.nsa.team10.asgproject.services.interfaces.IMetricsService;
import com.nsa.team10.asgproject.validation.ConflictException;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/stagemetrics")
    public List findStageMetrics()
    {
        var metrics = metricsService.findStageMetrics();
        return metrics;
    }

}
