package com.fernandez.basketball.resultsq.output;

import lombok.Getter;

@Getter
public class Away{
    public String name;
    public String image;

    @Override
    public String toString() {
        return "Away{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
