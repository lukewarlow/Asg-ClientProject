package com.nsa.team10.asgproject.repositories.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.repositories.daos.CountDao;

import java.util.List;

public interface IMetricsRepository {
    List<CountDao> countStages();

}
