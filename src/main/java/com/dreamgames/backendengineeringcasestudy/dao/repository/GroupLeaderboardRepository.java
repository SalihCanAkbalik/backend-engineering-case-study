package com.dreamgames.backendengineeringcasestudy.dao.repository;

import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupLeaderboardRepository extends JpaRepository<GroupLeaderboard,Long> {

    @Query("SELECT gl FROM GroupLeaderboard gl JOIN gl.usersList u WHERE u.id = :id")
    List<GroupLeaderboard> findAllByUserId(@Param("id") Long id);

    List<GroupLeaderboard> findByTournamentId(Long id);
}
