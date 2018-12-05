package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.repositories.daos.CountDao;

import java.util.List;

public interface IMetricsService {
     List<CountDao> countStages(FilteredPageRequest pageRequest);
}
