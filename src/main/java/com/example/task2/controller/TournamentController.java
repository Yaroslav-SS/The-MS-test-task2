package com.example.task2.controller;

import com.example.task2.entity.TournamentTableEntry;
import com.example.task2.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getTournamentTable(@RequestParam(required = false) Integer season,
                                     @RequestParam(required = false) String date,
                                     Model model) {
        if (season == null || date == null) {
            model.addAttribute("error", "Выберите сезон и дату для отображения таблицы.");
            return "tournament-table";
        }

        LocalDate localDate = LocalDate.parse(date);
        List<TournamentTableEntry> table = tournamentService.getTournamentTable(season, localDate);

        model.addAttribute("table", table);
        model.addAttribute("season", season);
        model.addAttribute("date", date);
        return "tournament-table";
    }
}
