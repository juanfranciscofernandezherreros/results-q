package com.fernandez.basketball.resultsq.controller;

import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import com.fernandez.basketball.resultsq.exception.MyEntityNotFoundException;
import com.fernandez.basketball.resultsq.repository.ResultsRepositoryImpl;
import com.fernandez.basketball.resultsq.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    public ResultsRepositoryImpl matchRepository;

    @Autowired
    public ResultsService resultsService;

    @GetMapping
    public ResponseEntity<Page<ResultsDTO>> findAllByDynamicCriteria(@RequestParam Map<String, String> params,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<ResultsDTO> partidos = resultsService.findAllByDynamicCriteria(params, page, size);
        return new ResponseEntity<>(partidos, HttpStatus.OK);
    }

    @GetMapping("{matchId}")
    public ResponseEntity<ResultsDTO> findById(@PathVariable("matchId") String matchId) {
        try {
            ResultsDTO fixture = resultsService.findMatchByMatchId(matchId);
            return new ResponseEntity<>(fixture, HttpStatus.OK);
        } catch (MyEntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<List<ResultsDTO>> saveAll(@RequestBody List<ResultsDTO> resultsDTOList) {
        List<ResultsDTO> resultsList = resultsService.saveAll(resultsDTOList);
        return new ResponseEntity<List<ResultsDTO>>(resultsList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<ResultsDTO>> updateAll(@RequestBody List<ResultsDTO> fixturesList) {
        List<ResultsDTO> fixturesDTOList = resultsService.updateAll(fixturesList);
        return new ResponseEntity<List<ResultsDTO>>(fixturesDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteByIds")
    public void deleteByIds(@RequestBody List<String> matchIds) {
        resultsService.deleteByIds(matchIds);
    }

}

