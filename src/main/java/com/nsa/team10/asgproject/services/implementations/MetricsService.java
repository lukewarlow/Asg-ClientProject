package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CountDao;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.repositories.implementations.MetricsRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IMetricsRepository;

import java.util.List;

public class MetricsService {
    private IMetricsRepository metricsRepository;

    public List<CountDao> countStages()
    {
        return metricsRepository.countStages();
    }
}
