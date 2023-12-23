package com.fernandez.basketball.resultsq.output;

import lombok.Getter;

import java.util.Date;

@Getter
public class MatchData{
    public Date transformedDate;
    public Home home;
    public Away away;
    public Result result;
    public String totalLocal;
    public String totalAway;
}
