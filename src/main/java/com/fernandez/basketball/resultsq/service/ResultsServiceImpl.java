package com.fernandez.basketball.resultsq.service;

import com.fernandez.basketball.resultsq.domain.ResultsDomainService;
import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import com.fernandez.basketball.resultsq.mapper.ResultsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ResultsServiceImpl implements ResultsService{

    @Autowired
    public ResultsDomainService resultsDomainService;

    @Autowired
    public ResultsMapper resultsMapper;
    @Override
    public List<ResultsDTO> saveAll(List<ResultsDTO> resultsDTOList) {
        log.info("Saving list of fixtures...");
        return resultsDomainService.saveAll(resultsMapper.mapListToDAO(resultsDTOList));
    }

    @Override
    public List<ResultsDTO> updateAll(List<ResultsDTO> resultsDTOList) {
        return resultsDomainService.updateAll(resultsMapper.mapListToDAO(resultsDTOList));
    }

    @Override
    public void deleteByIds(List<String> matchIds) {
        resultsDomainService.deleteByIds(matchIds);
    }

    @Override
    public ResultsDTO findMatchByMatchId(String matchId) {
        return resultsDomainService.findById(matchId);
    }

    @Override
    public Page<ResultsDTO> findAllByDynamicCriteria(Map<String, String> queryParams, int page, int size) {
        return resultsDomainService.findAllByDynamicCriteria(queryParams,page,size);
    }
}
