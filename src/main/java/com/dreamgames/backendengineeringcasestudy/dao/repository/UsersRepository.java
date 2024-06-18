package com.dreamgames.backendengineeringcasestudy.dao.repository;

import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dto.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.userLevel >= 20 AND u.coins >= 1000")
    List<Users> findByEnterTournamentTrueAndIsAlreadyEnteredFalse();

    @Query("SELECT u FROM Users u WHERE u.userLevel >= 20 AND u.coins >= 1000")
    List<Users> findByUsersCanEnterTournaments();

}
