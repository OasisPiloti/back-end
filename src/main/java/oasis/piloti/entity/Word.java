package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String koreanWord; // 한국 단어

    @Column(nullable = false)
    private String russianWord; // 러시아 문장

    @Column(nullable = false)
    private String audioUrl; // 음성 주소

    // 연관관계 주인
    // 양방향 연관관계
    @ManyToOne
    @JoinColumn(name = "study_card_id")
    private StudyCard studyCard;

    @Builder
    private Word(String koreanWord, String russianWord, String audioUrl, StudyCard studyCard) {
        this.koreanWord = koreanWord;
        this.russianWord = russianWord;
        this.audioUrl = audioUrl;
        assignStudyCard(studyCard);
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static Word buildWord(String koreanWord, String russianWord, String audioUrl, StudyCard studyCard) {
        return Word.builder()
                .koreanWord(koreanWord)
                .russianWord(russianWord)
                .audioUrl(audioUrl)
                .studyCard(studyCard)
                .build();
    }

    // Word N <-> StudyCard 1
    // 양방향 연관관계 편의 메서드
    public void assignStudyCard(StudyCard studyCard) {
        if (this.studyCard != null) {
            this.studyCard.getWords().remove(this);
        }

        this.studyCard = studyCard;

        if (!studyCard.getWords().contains(this)) {
            studyCard.addWord(this);
        }
    }
}
