package com.example.band_schadule.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "Schedules", indexes = {
        @Index(name = "idx_community_id", columnList = "communityId")})
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheduleName;
    private LocalDateTime scheduleTime;
    private String meetingPlace;
    private String price;
    private int maxParticipation;
    private int participant;
    private String interest;
    private Long memberId;
    private String memberName;
    private String memberImage;
    private Long communityId;



}
