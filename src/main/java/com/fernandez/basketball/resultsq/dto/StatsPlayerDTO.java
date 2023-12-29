package com.fernandez.basketball.resultsq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatsPlayerDTO {
    public String name;
    public StatsDTO stats;
}
