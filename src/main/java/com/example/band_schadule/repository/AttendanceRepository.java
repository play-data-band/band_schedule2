package com.example.band_schadule.repository;

import com.example.band_schadule.domain.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findByCommunityMemberId(Long memberId);
}
