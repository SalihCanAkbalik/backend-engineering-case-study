package com.dreamgames.backendengineeringcasestudy.scheduler;

import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TournamentScheduler {

    @Autowired
    private TournamentService tournamentService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleDailyTournamentCreation() {
        tournamentService.createTournament();
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void endDailyTournamentStats() {
        tournamentService.resetUserStates();
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 60000) // Her dakika
    public void createTournamentGroups() {
        tournamentService.createTournament();
    }
}
/*@Component
@Slf4j
@AllArgsConstructor
public class TournamentScheduler {
    private TournamentService tournamentService;

    private TournamentWebSocketHandler webSocketHandler;

    private static final LocalTime START_TIME = LocalTime.of(0, 0); // 00:00
    private static final LocalTime END_TIME = LocalTime.of(20, 0);  // 20:00

    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void scheduleTournamentCreation() throws Exception {
        LocalTime now = LocalTime.now();
        if (!now.isBefore(START_TIME) && !now.isAfter(END_TIME)) {
            tournamentService.createTournament();
            webSocketHandler.notifyUsers("New tournament created!");
        } else {
            System.out.println("Outside of scheduled time range. Tournament will not be created.");
        }
    }
}*/
