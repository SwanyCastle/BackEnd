package com.devonoff.entity;

import com.devonoff.type.LoginType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(nullable = false, unique = true)
  private String username; // 사용자 닉네임

  @Column(nullable = false, unique = true)
  private String email; // 사용자 이메일


  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private LoginType loginType = LoginType.GENERAL; // 로그인 타입 (GENERAL, GOOGLE, NAVER 등)

  @Column(nullable = true)
  private String providerId; // 소셜용

  @Column(nullable = true, name = "profile_image_url")
  private String profileImage; // 기본 역할

  @Column(name = "is_active", nullable = false)
  private Boolean isActive = true;

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now(); // 가입일

  @Column(name = "update_at", nullable = false)
  private LocalDateTime updatedAt = LocalDateTime.now(); // 수정 날짜

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }


}