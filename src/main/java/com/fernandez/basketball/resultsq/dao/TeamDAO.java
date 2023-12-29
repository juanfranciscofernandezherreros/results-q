package com.fernandez.basketball.resultsq.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamDAO {
    public String name;
    public byte[] image;
}
