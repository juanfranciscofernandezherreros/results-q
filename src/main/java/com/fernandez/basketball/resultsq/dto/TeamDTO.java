package com.fernandez.basketball.resultsq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamDTO {
    public String name;
    public byte[] image;
}
