package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;
import com.nsa.team10.asgproject.repositories.interfaces.IMetricsRepository;
import com.nsa.team10.asgproject.services.interfaces.IMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricsService implements IMetricsService
{
    private final IMetricsRepository metricsRepository;

    @Autowired
    public MetricsService(IMetricsRepository metricsRepository)
    {
        this.metricsRepository = metricsRepository;
    }

    public List<StageMetricsDao> findStageMetrics()
    {
        return metricsRepository.findStageMetrics();
    }
}
