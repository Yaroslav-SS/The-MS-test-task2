package com.example.task2.service;

import com.example.task2.entity.Match;
import com.example.task2.entity.Team;
import com.example.task2.entity.TournamentTableEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.task2.repository.MatchRepository;
import com.example.task2.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TournamentServiceTest {
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void testTournamentTable() {
        Team team1 = teamRepository.save(new Team("Бродяги"));
        Team team2 = teamRepository.save(new Team("Феникс"));

        matchRepository.save(new Match(2025, LocalDate.now(), team1, team2, 2, 1));

        List<TournamentTableEntry> table = tournamentService.getTournamentTable(2025, LocalDate.now());
        assertEquals(2, table.size());
        assertEquals("Team A", table.get(0).getTeamName());
        assertEquals(3, table.get(0).getPoints());
    }
}