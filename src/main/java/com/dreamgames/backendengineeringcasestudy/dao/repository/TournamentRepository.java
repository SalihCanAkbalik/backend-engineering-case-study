package com.dreamgames.backendengineeringcasestudy.dao.repository;

import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Long> {

    @Query("SELECT t FROM Tournament t WHERE t.startTime BETWEEN :startOfDay AND :endOfDay")
    Optional<Tournament> findTournamentForToday(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
