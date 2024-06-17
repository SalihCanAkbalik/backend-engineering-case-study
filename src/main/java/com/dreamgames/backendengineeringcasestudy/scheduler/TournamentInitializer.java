package com.dreamgames.backendengineeringcasestudy.scheduler;

import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TournamentInitializer implements CommandLineRunner {

    @Autowired
    private TournamentService tournamentService;

    @Override
    public void run(String... args) throws Exception {
        tournamentService.createTournament();
    }
}
