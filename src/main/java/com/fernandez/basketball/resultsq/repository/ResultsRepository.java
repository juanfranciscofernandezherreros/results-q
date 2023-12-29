package com.fernandez.basketball.resultsq.repository;

import com.fernandez.basketball.resultsq.dao.ResultsDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultsRepository extends MongoRepository<ResultsDAO, Long> {

    void deleteByMatchIdIn(List<String> matchIds);
    @Query("{'matchId': { $in: ?0 }}")
    List<ResultsDAO> findAllByMatchIdIn(List<String> matchIds);

    Optional<ResultsDAO> findByMatchId(String matchId);
}