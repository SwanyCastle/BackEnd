package com.devonoff.domain.qnapost.dto;


import com.devonoff.domain.qnapost.entity.QnaPost;
import com.devonoff.domain.user.dto.UserDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class QnaPostDto {

  private Long id;
  private UserDto user;
  private String title;
  private String content;
  private String thumbnailImgUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static QnaPostDto fromEntity(QnaPost qnaPost) {
    return QnaPostDto.builder()
        .id(qnaPost.getId())
        .user(UserDto.fromEntity(qnaPost.getUser())) // 간단한 사용자 정보
        .title(qnaPost.getTitle())
        .content(qnaPost.getContent())
        .thumbnailImgUrl(qnaPost.getThumbnailUrl())
        .createdAt(qnaPost.getCreatedAt())
        .updatedAt(qnaPost.getUpdatedAt())
        .build();
  }
}