package com.fernandez.basketball.resultsq.domain;

import com.fernandez.basketball.resultsq.dao.ResultsDAO;
import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import com.fernandez.basketball.resultsq.exception.MyEntityNotFoundException;
import com.fernandez.basketball.resultsq.mapper.ResultsMapper;
import com.fernandez.basketball.resultsq.repository.ResultsRepository;
import com.fernandez.basketball.resultsq.repository.ResultsRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ResultsDomainServiceImpl implements ResultsDomainService{

    @Autowired
    public ResultsRepository resultsRepository;

    @Autowired
    public ResultsRepositoryImpl resultsRepositoryImpl;

    @Autowired
    public ResultsMapper resultsMapper;

    @Override
    public List<ResultsDTO> saveAll(List<ResultsDAO> resultsDAOList) {
        log.info("Saving list of results...");
        return resultsMapper.mapListToDTO(resultsRepository.saveAll(resultsDAOList));
    }

    @Override
    public List<ResultsDTO> updateAll(List<ResultsDAO> updatedFixturesList) {
        log.info("Updating list of results...");
        List<ResultsDAO> updatedEntities = resultsRepository.saveAll(updatedFixturesList);
        return resultsMapper.mapListToDTO(updatedEntities);
    }

    @Override
    public void deleteByIds(List<String> matchIds) {
        log.info("Deleting results by matchIds: {}", matchIds);
        resultsRepository.deleteByMatchIdIn(matchIds);
    }

    @Override
    public ResultsDTO findById(String matchId) {
        log.info("Finding fixture by matchId: {}", matchId);
        Optional<ResultsDAO> optionalFixturesDAO = resultsRepository.findByMatchId(matchId);
        // Use orElseThrow to throw EntityNotFoundException if the entity is not present
        ResultsDAO fixturesDAO = optionalFixturesDAO.orElseThrow(() ->
                new MyEntityNotFoundException("Results with matchId " + matchId + " not found"));
        // Map the FixturesDAO to a FixturesDTO and return it
        return resultsMapper.mapToDTO(fixturesDAO);
    }

    @Override
    public Page<ResultsDTO> findAllByDynamicCriteria(Map<String, String> queryParams, int page, int size) {
        log.info("Finding results by dynamic criteria: {}, page: {}, size: {}", queryParams, page, size);
        return resultsMapper.mapToPageDTO(resultsRepositoryImpl.findAllByDynamicCriteriaWithPagination(queryParams, page, size));
    }
}
