package com.fernandez.basketball.resultsq.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MatchData {
    private String date;
    private Date transformedDate;
    private Team home;
    private Team away;
    private Result result;
}
