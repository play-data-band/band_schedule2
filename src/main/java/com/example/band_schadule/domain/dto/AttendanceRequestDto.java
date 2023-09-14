package com.example.band_schadule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequestDto {
    private Long memberId;
    private Long scheduleId;
    private String useYn;
    private Long communityId;

}
