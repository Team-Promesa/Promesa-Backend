package com.promesa.promesa.domain.inquiry.domain;

import com.promesa.promesa.domain.artist.domain.Artist;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    // SQL로 Q&A 직접 등록할 경우 해당 코드는 필요 없으므로 일단 주석 처리
    /*
    @Builder
    public Inquiry(String question, String answer, Artist artist) {
        this.question = question;
        this.answer = answer;
        this.artist = artist;
    }

    public void updateAnswer(String answer) {
        this.answer = answer;
    }
     */
}

