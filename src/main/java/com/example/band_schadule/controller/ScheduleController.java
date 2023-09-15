package com.example.band_schadule.controller;

import com.example.band_schadule.common.RestResult;
import com.example.band_schadule.domain.dto.AttendanceRequestDto;
import com.example.band_schadule.domain.request.ScheduleRequest;
import com.example.band_schadule.domain.dto.ScheduleDto;
import com.example.band_schadule.domain.response.ScheduleResponse;
import com.example.band_schadule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("{communityId}")
    public void save(@PathVariable("communityId") Long communityId,
                     @RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.save(communityId, scheduleRequest);
    }

    @GetMapping("/community/{id}")
    public List<ScheduleResponse> findAll(@PathVariable("id") Long communityId) {
        List<ScheduleResponse> all = scheduleService.findAll(communityId);
        return all;
    }

    @GetMapping
    public List<ScheduleResponse> findAllSchedule() {
        return scheduleService.findAllSchedule();
    }

    //http://localhost:8080/api/v1/schedule/upcoming-by-interest?interest=사교/인맥&interest=공예/만들기
    @PostMapping("/upcomingByInterest")
    public List<ScheduleResponse> getScheduleByInterest(@RequestBody ScheduleDto scheduleDto) {
        System.out.println(scheduleDto.getInterest());
        List<ScheduleResponse> upcomingSchedulesByInterest = scheduleService.findUpcomingSchedulesByInterest(scheduleDto.getInterest());
        return upcomingSchedulesByInterest;
    }

    @GetMapping("/communityId/{communityId}")
    public List<ScheduleResponse> findBycommunityId(@PathVariable("communityId") Long communityId) {
        return scheduleService.findAllBycommunityId(communityId);
    }


    @GetMapping("{id}")
    public ScheduleResponse findById(@PathVariable("id") Long id) {
        return scheduleService.findById(id);
    }

    @PostMapping("/attendance")
    public ResponseEntity<RestResult<Object>> toggleAttendance(
            @RequestBody AttendanceRequestDto attendanceRequestDto) {
        return scheduleService.toggleAttendance(attendanceRequestDto);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        scheduleService.deleteByScheduleId(scheduleId);
    }

    @PutMapping("/{communityId}/{scheduleId}")
    public void updateSchedule(@PathVariable("communityId") Long communityId,
                               @PathVariable("scheduleId") Long scheduleId,
                               @RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.updateSchedule(communityId, scheduleId, scheduleRequest);
    }


}
