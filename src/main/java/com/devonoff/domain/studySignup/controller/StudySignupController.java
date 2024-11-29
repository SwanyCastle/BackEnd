package com.devonoff.domain.studySignup.controller;

import com.devonoff.domain.studySignup.dto.StudySignupCreateDto;
import com.devonoff.domain.studySignup.dto.StudySignupCreateDto.Response;
import com.devonoff.domain.studySignup.dto.StudySignupDto;
import com.devonoff.domain.studySignup.service.StudySignupService;
import com.devonoff.type.StudySignupStatus;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-signup")
public class StudySignupController {

  private final StudySignupService studySignupService;

  // 스터디 신청
  @PostMapping
  public ResponseEntity<Response> createStudySignup(
      @RequestBody @Valid StudySignupCreateDto.Request request) {
    StudySignupCreateDto.Response response = studySignupService.createStudySignup(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // 신청 상태 관리(승인/거절)
  @PatchMapping("/{signupId}")
  public ResponseEntity<Void> updateSignupStatus(
      @PathVariable Long signupId, @RequestParam StudySignupStatus newStatus) {
    studySignupService.updateSignupStatus(signupId, newStatus);
    return ResponseEntity.noContent().build();
  }

  // 상태별 신청 목록 조회
  @GetMapping
  public ResponseEntity<List<StudySignupDto>> getSignupList(
      @RequestParam Long studyPostId,
      @RequestParam(required = false) StudySignupStatus status) {
    List<StudySignupDto> signupList = studySignupService.getSignupList(studyPostId, status);
    return ResponseEntity.ok(signupList);
  }

  // 신청 취소
  @DeleteMapping("/{signupId}")
  public ResponseEntity<Void> cancelSignup(
      @PathVariable Long signupId,
      @RequestParam Long userId) {
    studySignupService.cancelSignup(signupId, userId);
    return ResponseEntity.noContent().build();
  }
}