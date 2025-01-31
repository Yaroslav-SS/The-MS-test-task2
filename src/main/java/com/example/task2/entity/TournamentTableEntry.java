package com.example.task2.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentTableEntry {
    private String teamName;
    private int games;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public TournamentTableEntry(String teamName) {
        this.teamName = teamName;
    }

    public void addGame() { games++; }
    public void addWin() { wins++; points += 3; }
    public void addDraw() { draws++; points += 1; }
    public void addLoss() { losses++; }
}