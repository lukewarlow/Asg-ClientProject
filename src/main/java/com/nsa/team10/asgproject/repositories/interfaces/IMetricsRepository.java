package com.nsa.team10.asgproject.repositories.interfaces;

import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;

import java.util.List;

public interface IMetricsRepository
{
    List<StageMetricsDao> findStageMetrics();
}
