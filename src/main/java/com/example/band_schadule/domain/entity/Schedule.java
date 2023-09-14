package com.example.band_schadule.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "Schedules")
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
    private String memberName;
    private String memberImage;
    private Long communityId;

    public void setParticipant(int participant) {
        if (participant < 0) {
            this.participant = 0;
        } else {
            this.participant = participant;
        }
    }

}
