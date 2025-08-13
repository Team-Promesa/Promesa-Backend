package com.promesa.promesa.domain.artist.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.common.exception.ValidationException;
import com.promesa.promesa.domain.exhibition.domain.ExhibitionArtist;
import com.promesa.promesa.domain.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="artist")
public class Artist extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String subname;

    @Column(name = "profileImage_key")
    private String profileImageKey;

    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String description;

    private String insta;

    @Column(name = "wish_count")
    private int wishCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<ExhibitionArtist> exhibitionArtists = new ArrayList<>();

    public void increaseWishCount() {
        this.wishCount++;
    }

    public void decreaseWishCount() {
        if (this.wishCount > 0) {
            this.wishCount -= 1;
        } else {
            this.wishCount = 0; // wishCount가 음수가 되지 않도록 방지
        }
    }

    @Builder
    public Artist(String name, String subname, String profileImageKey, String description, String insta,
                  int wishCount, Member member) {
        this.name = name;
        this.subname = subname;
        this.profileImageKey = profileImageKey;
        this.description = description;
        this.insta = insta;
        this.wishCount = wishCount;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
        if (member.getArtist() != this) {
            member.setArtist(this);
        }
    }

    public void setProfileImageKey(String targetKey) {
        this.profileImageKey = targetKey;
    }

    public void setName(String name){
        if (name == null || name.isBlank()) {
            throw ValidationException.EXCEPTION;
        }
        this.name = name;
    }

    public void setSubname(String subname) {
        if (subname != null && subname.isBlank()) { // null 값 허용
            throw ValidationException.EXCEPTION;
        }
        this.subname = subname;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw ValidationException.EXCEPTION;
        }
        this.description = description;
    }

    public void setInsta(String insta) {
        if (insta != null && insta.isBlank()) { // null 값 허용
            throw ValidationException.EXCEPTION;
        }
        this.insta = insta;
    }
}
