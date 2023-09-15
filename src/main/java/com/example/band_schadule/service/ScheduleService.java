package com.example.band_schadule.service;


import com.example.band_schadule.common.RestError;
import com.example.band_schadule.common.RestResult;
import com.example.band_schadule.domain.dto.AttendanceRequestDto;
import com.example.band_schadule.domain.entity.Attendance;
import com.example.band_schadule.domain.entity.Schedule;
import com.example.band_schadule.domain.response.ScheduleResponse;
import com.example.band_schadule.repository.AttendanceRepository;
import com.example.band_schadule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AttendanceRepository attendanceRepository;

    public List<ScheduleResponse> findUpcomingSchedulesByInterest(List<String> interest) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Schedule> scheduleList = scheduleRepository.getScheduleByMain(interest, currentTime);
        return scheduleList.stream().map(ScheduleResponse::new).collect(Collectors.toList());
    }

    public ScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        return new ScheduleResponse(schedule);
    }

    //스케줄 참석/불참
    public ResponseEntity<RestResult<Object>> toggleAttendance(AttendanceRequestDto attendanceRequestDto) {
        Long memberId = attendanceRequestDto.getMemberId();
        Long communityId = attendanceRequestDto.getCommunityId();
        Long scheduleId = attendanceRequestDto.getScheduleId();
        String useYn = attendanceRequestDto.getUseYn();

        // 커뮤니티멤버 테이블 -> 커뮤니티에 가입된 멤버가 모여있는 테이블

        // 유저가 일정에 참석을 하는데 ..
        // 1. 커뮤니티멤버에 멤버ID랑 커뮤니티 ID를 기준으로 데이터를 찾는다.
        // 2. 만약 데이터가 없다면 ? 그 유저는 해당 커뮤니티에 가입된게 아니므로 일정에 참석할 수 없다.
        // 3. 있다면 ? -> 유저가 참석 버튼을 눌렀다면 ?
        // 4. attendace 테이블 ( 일정참석 테이블 ) 에 데이터를 넣어준다. ( 참석 시킴 ) 넣어준 후 참석 인원 증가
        // 5. 하지만 유저가 취소 버튼을 눌렀다면 ?
        // 6. 취소버튼을 누른 유저의 memberId 를 기준으로 attandance (참석테이블) 에서 데이터를 찾는다.
        // 7. 찾았다면 해당 데이터를 삭제한다. ( 참석 취소 ) 넣어준 후 참석 인원 감소


       /* CommunityMember communityMember = communityMemberRepository.findByCommunityIdAndMemberId(communityId, memberId).orElseThrow(
                () -> new RuntimeException("CommunityMember not found!"));*/
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found!"));

//        Optional<Attendance> attendance = attendanceRepository.findByCommunityMemberAndSchedule(communityMemberId, schedule);

            if ("Y".equals(useYn)) {
                //참석할 수 있는 최대 인원과 현재 참석한 인원 비교
                if (schedule.getParticipant() < schedule.getMaxParticipation()) {
                    //참석
                    Attendance attendance = Attendance.builder()
                            .schedule(schedule)
                            .useYn(attendanceRequestDto.getUseYn())
                            .communityMemberId(attendanceRequestDto.getMemberId())
                            .build();
                    attendanceRepository.save(attendance);

                    // 참석 인원 증가
                    schedule.setParticipant(schedule.getParticipant() + 1);

                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new RestResult<>("BAD_REQUEST", new RestError("BAD_REQUEST", "일정 정원이 초과되었습니다.")));
                }

            } else if ("N".equals(useYn)) {
                //불참
                Attendance attendance = attendanceRepository.findByCommunityMemberId(attendanceRequestDto.getMemberId());

                // 참석 인원 감소
                schedule.setParticipant(schedule.getParticipant() - 1);

                attendanceRepository.delete(attendance);
            }


        return ResponseEntity.ok(new RestResult<>("success", "참석이 완료 되었습니다."));
    }


    public List<ScheduleResponse> findAllBycommunityId(Long communityId) {

        List<Schedule> byCommunityId = scheduleRepository.findAllBycommunityId(communityId);

        return byCommunityId.stream().map(ScheduleResponse::new).toList();
    }
}


