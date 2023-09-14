package com.example.band_schadule.controller;

import com.example.band_schadule.domain.response.ScheduleResponse;
import com.example.band_schadule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //http://localhost:8080/api/v1/schedule/upcoming-by-interest?interest=사교/인맥&interest=공예/만들기
    @PostMapping("/upcoming-by-interest")
    public List<ScheduleResponse> getScheduleByInterest(@RequestBody List<String> interest) {
        List<ScheduleResponse> upcomingSchedulesByInterest = scheduleService.findUpcomingSchedulesByInterest(interest);
        return upcomingSchedulesByInterest;
    }

}