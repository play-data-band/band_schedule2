package com.example.band_schadule.repository;

import com.example.band_schadule.domain.entity.Attendance;
import com.example.band_schadule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findByMemberId(Long memberId);

    Optional<Attendance> findByMemberIdAndSchedule(Long memberId, Schedule schedule);

}
