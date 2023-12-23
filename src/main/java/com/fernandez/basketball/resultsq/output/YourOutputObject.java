package com.fernandez.basketball.resultsq.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RESULTS")
@ToString
public class YourOutputObject {
    public MatchData matchData;

    public MatchData getMatchData() {
        return matchData;
    }

    public void setMatchData(MatchData matchData) {
        this.matchData = matchData;
    }

    @Override
    public String toString() {
        return "YourOutputObject{" +
                "matchData=" + matchData +
                '}';
    }
}
