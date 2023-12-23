package com.fernandez.basketball.resultsq.controller;

import com.fernandez.basketball.resultsq.dto.Match;
import com.fernandez.basketball.resultsq.repository.CustomMatchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    private CustomMatchRepositoryImpl matchRepository;

    @GetMapping
    public ResponseEntity<List<Match>> obtenerPartidosPorFechasYEquipos(@RequestParam Map<String, String> params) {
        Map<String, String> queryParams = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryParams.put(entry.getKey(), entry.getValue());
        }
        List<Match> partidos = matchRepository.findAllByDynamicCriteria(queryParams);
        return new ResponseEntity<>(partidos, HttpStatus.OK);
    }
}

