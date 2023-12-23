package com.fernandez.basketball.resultsq.output;

import lombok.Getter;

@Getter
public class Home{
    public String name;
    public String image;

    @Override
    public String toString() {
        return "Home{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
