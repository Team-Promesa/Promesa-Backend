package com.promesa.promesa.domain.artist.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.domain.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "artist_id")
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "profileImage_url")
    private String profileImageUrl;

    @NotBlank
    private String description;

    private String insta;

    @Column(name = "wish_count")
    private int wishCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}