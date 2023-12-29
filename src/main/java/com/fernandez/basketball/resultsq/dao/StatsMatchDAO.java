package com.fernandez.basketball.resultsq.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatsMatchDAO {
    public String category;
    public String homeValue;
    public String awayValue;
    public String homeChartWidth;
    public String awayChartWidth;
}
