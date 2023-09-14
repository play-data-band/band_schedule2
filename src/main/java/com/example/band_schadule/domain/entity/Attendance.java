package com.example.band_schadule.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long communityMemberId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private String useYn;
    private String memberImg;
    private String memberName;

}
