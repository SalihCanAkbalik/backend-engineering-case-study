package com.dreamgames.backendengineeringcasestudy.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Tournament")
@Getter
@Setter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalDateTime endTime;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament",cascade = CascadeType.MERGE)
    private List<Users> users;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament",cascade = CascadeType.MERGE)
    private List<GroupLeaderboard> groupLeaderboard;

}
