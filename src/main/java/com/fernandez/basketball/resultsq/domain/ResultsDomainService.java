package com.fernandez.basketball.resultsq.domain;

import com.fernandez.basketball.resultsq.dao.ResultsDAO;
import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ResultsDomainService {
    List<ResultsDTO> saveAll(List<ResultsDAO> mapListToDAO);

    List<ResultsDTO> updateAll(List<ResultsDAO> mapListToDAO);

    void deleteByIds(List<String> matchIds);

    ResultsDTO findById(String matchId);

    Page<ResultsDTO> findAllByDynamicCriteria(Map<String, String> queryParams, int page, int size);
}
