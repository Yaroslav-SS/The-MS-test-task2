package com.example.task2.controller;

import com.example.task2.dto.MatchDto;
import com.example.task2.entity.Match;
import com.example.task2.entity.Team;
import com.example.task2.repository.TeamRepository;
import com.example.task2.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;
    private final TeamRepository teamRepository;

    public MatchController(MatchService matchService, TeamRepository teamRepository) {
        this.matchService = matchService;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/register")
    public String showRegisterMatchPage(Model model) {
        model.addAttribute("match", new MatchDto());
        return "register-match";
    }

    @PostMapping("/register")
    public String registerMatch(@ModelAttribute MatchDto matchDto, Model model) {
        Team homeTeam = teamRepository.findByName(matchDto.getHomeTeam())
                .orElseGet(() -> {
                    Team team = new Team();
                    team.setName(matchDto.getHomeTeam());
                    return teamRepository.save(team);
                });

        Team awayTeam = teamRepository.findByName(matchDto.getAwayTeam())
                .orElseGet(() -> {
                    Team team = new Team();
                    team.setName(matchDto.getAwayTeam());
                    return teamRepository.save(team);
                });

        Match match = new Match();
        match.setSeason(matchDto.getSeason());
        match.setDate(LocalDate.parse(matchDto.getDate()));
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeGoals(matchDto.getHomeGoals());
        match.setAwayGoals(matchDto.getAwayGoals());

        matchService.saveMatch(match);

        return "redirect:/?success=true";
    }


}
