package com.fernandez.basketball.resultsq.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "RESULTS")
public class Match {
    @Id
    private String id;
    private MatchData matchData;
}

