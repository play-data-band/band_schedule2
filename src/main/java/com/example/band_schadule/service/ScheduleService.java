package com.example.band_schadule.service;


import com.example.band_schadule.domain.entity.Schedule;
import com.example.band_schadule.domain.response.ScheduleResponse;
import com.example.band_schadule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponse> findUpcomingSchedulesByInterest(List<String> interest) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Schedule> scheduleList = scheduleRepository.getScheduleByMain(interest, currentTime);
        return scheduleList.stream().map(ScheduleResponse::new).collect(Collectors.toList());
    }

}


