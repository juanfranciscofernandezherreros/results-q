package com.fernandez.basketball.resultsq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatsMatchDTO {
    public String category;
    public String homeValue;
    public String awayValue;
    public String homeChartWidth;
    public String awayChartWidth;
}
