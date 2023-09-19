package com.example.band_schadule.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Builder
@Table( indexes = {
        @Index(name = "idx_schedule_id", columnList = "schedule_id"),
        @Index(name = "idx_member_id", columnList = "memberId")})
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( indexes = {
        @Index(name = "idx_schedule_id", columnList = "schedule_id"),
        @Index(name = "idx_member_id", columnList = "memberId")
})
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private String useYn;
    private String memberImg;
    private String memberName;

}
