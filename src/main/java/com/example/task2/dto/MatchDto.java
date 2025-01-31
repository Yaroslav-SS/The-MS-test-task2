package com.example.task2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {
    private int season;
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeGoals;
    private int awayGoals;
}
