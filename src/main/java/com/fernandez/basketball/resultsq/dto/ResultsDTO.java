package com.fernandez.basketball.resultsq.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResultsDTO {
    private String matchId;
    public MatchDataDTO matchData;
    public List<StatsPlayerDTO> statsPlayer;
    public List<StatsMatchDTO> statsMatch_all;
    public List<StatsMatchDTO> statsMatch_first;
    public List<StatsMatchDTO> statsMatch_second;
    public List<StatsMatchDTO> statsMtach_thirst;
    public List<StatsMatchDTO> statsMtach_four;
    public List<PointByPointDTO> pointByPoint;

}



