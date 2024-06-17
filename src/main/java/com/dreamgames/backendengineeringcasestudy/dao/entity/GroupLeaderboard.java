package com.dreamgames.backendengineeringcasestudy.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "GroupLeaderboard")
@Getter
@Setter
public class GroupLeaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SCORES", length = 4)
    private Integer scores;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupLeaderboard", cascade = CascadeType.MERGE)
    private List<Users> usersList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}

