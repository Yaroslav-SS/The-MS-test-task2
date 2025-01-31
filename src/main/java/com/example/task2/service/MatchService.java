package com.example.task2.service;

import com.example.task2.entity.Match;
import org.springframework.stereotype.Service;
import com.example.task2.repository.MatchRepository;
import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> getMatchesBySeason(int season) {
        return matchRepository.findBySeason(season);
    }
}
