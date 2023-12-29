package com.fernandez.basketball.resultsq.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PointByPointDAO {
    public String score;
}
