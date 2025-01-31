package com.example.task2.service;

import com.example.task2.entity.Match;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MatchServiceTest {
    @Autowired
    private MatchService matchService;

    @Test
    void testSaveMatch() {
        Match match = new Match();
        match.setSeason(2025);
        match.setDate(LocalDate.now());
        match.setHomeGoals(2);
        match.setAwayGoals(1);
        Match savedMatch = matchService.saveMatch(match);
        assertNotNull(savedMatch.getId());
    }
}