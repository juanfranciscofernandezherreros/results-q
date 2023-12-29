package com.fernandez.basketball.resultsq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class MatchDataDTO {
    public Instant date;
    public TeamDTO home;
    public TeamDTO away;
    public ResultDTO result;
    public String totalLocal;
    public String firstLocal;
    public String secondLocal;
    public String thirstLocal;
    public String fourthLocal;
    public String extraLocal;
    public String totalAway;
    public String firstAway;
    public String secondAway;
    public String thirstAway;
    public String fourthAway;
    public String extraAway;
}

