package com.example.band_schadule.repository;

import com.example.band_schadule.domain.entity.Attendance;
import com.example.band_schadule.domain.entity.Schedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findByMemberId(Long memberId);



    Integer countAttendanceByMemberIdAndSchedule(Long memberId, Schedule schedule);

    List<Attendance> findAllByScheduleId(Long id);
}
