package com.fernandez.basketball.resultsq.service;

import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ResultsService {
    List<ResultsDTO> saveAll(List<ResultsDTO> resultsDTOList);

    List<ResultsDTO> updateAll(List<ResultsDTO> fixturesList);

    void deleteByIds(List<String> matchIds);

    ResultsDTO findMatchByMatchId(String matchId);

    Page<ResultsDTO> findAllByDynamicCriteria(Map<String, String> queryParams, int page, int size);

}
