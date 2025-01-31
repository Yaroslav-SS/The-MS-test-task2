package com.example.task2.service;

import com.example.task2.entity.Match;
import com.example.task2.entity.Team;
import com.example.task2.entity.TournamentTableEntry;
import org.springframework.stereotype.Service;
import com.example.task2.repository.MatchRepository;
import com.example.task2.repository.TeamRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TournamentService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public TournamentService(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public List<TournamentTableEntry> getTournamentTable(int season, LocalDate date) {
        List<Match> matches = matchRepository.findBySeason(season)
                .stream()
                .filter(m -> !m.getDate().isAfter(date))
                .toList();

        Map<Team, TournamentTableEntry> table = new HashMap<>();

        for (Match match : matches) {
            table.putIfAbsent(match.getHomeTeam(), new TournamentTableEntry(match.getHomeTeam().getName()));
            table.putIfAbsent(match.getAwayTeam(), new TournamentTableEntry(match.getAwayTeam().getName()));

            TournamentTableEntry homeEntry = table.get(match.getHomeTeam());
            TournamentTableEntry awayEntry = table.get(match.getAwayTeam());

            homeEntry.addGame();
            awayEntry.addGame();

            if (match.getHomeGoals() > match.getAwayGoals()) {
                homeEntry.addWin();
                awayEntry.addLoss();
            } else if (match.getHomeGoals() < match.getAwayGoals()) {
                homeEntry.addLoss();
                awayEntry.addWin();
            } else {
                homeEntry.addDraw();
                awayEntry.addDraw();
            }
        }

        return table.values()
                .stream()
                .sorted(Comparator.comparing(TournamentTableEntry::getPoints).reversed())
                .toList();
    }
}