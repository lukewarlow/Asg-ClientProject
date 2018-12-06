package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;

import java.util.List;

public interface IMetricsService
{
    List<StageMetricsDao> findStageMetrics();
}
