package com.dreamgames.backendengineeringcasestudy.dao.entity;

import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "AGE", nullable = false, length = 4)
    private Integer age;

    @Column(name = "USER_LEVEL", nullable = false, length = 6)
    private Integer userLevel;

    @Column(name = "COINS", nullable = false, length = 16)
    private Integer coins;

    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY", nullable = false)
    private Country country;

    @Column(name = "ENTER_TOURNAMENT")
    private Boolean enterTournament;

    @Column(name = "ALREADY_ENTERED")
    private Boolean isAlreadyEntered;

    @Column(name = "IS_GET_LAST_TOURNAMENT_REWARD")
    private Boolean isGetLastTournamentReward;

    @Column(name = "GROUP_SCORES")
    private Integer groupScores;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_leaderboard_id")
    private GroupLeaderboard groupLeaderboard;

}

