package com.example.band_schadule.repository;

import com.example.band_schadule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s " +
            "from Schedule s " +
            "where  Date(s.scheduleTime) = Date(:currentTime) " +
            "and s.scheduleTime > :currentTime " +
            "and s.interest in :interests " +
            "order by s.scheduleTime asc")
    List<Schedule> getScheduleByMain(List<String> interests, @Param("currentTime") LocalDateTime currentTime);

    List<Schedule> findByCommunityId(Long communityId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberName = :memberName " +
            "where s.memberId = :memberId")
    void updateAlbumdMemberName(@Param("memberName") String memberName, @Param("memberId") Long memberId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberImage = :memberImage " +
            "where s.memberId = :memberId")
    void updateAlbumMemberImage(@Param("memberImage") String memberImage, @Param("memberId") Long memberId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberImage = :memberImage," +
            "s.memberName = :memberName " +
            "where s.memberId = :memberId")
    void updateAlbumMemberImageAndMemberName(@Param("memberName")String memberName, @Param("memberImage") String memberImage, @Param("memberId") Long memberId);


    List<Schedule> findAllBycommunityId(Long communityId);

}

