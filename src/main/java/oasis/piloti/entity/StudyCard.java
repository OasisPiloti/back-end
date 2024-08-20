package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String koreanTitle; // 한국 제목

    @Column(nullable = false)
    private String russianTitle; // 러시아 제목

    @Column(nullable = false)
    private String koreanDescription; // 한국 설명

    @Column(nullable = false)
    private String russianDescription; // 러시아 설명

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studyCard")
    private List<Dialogue> dialogues = new ArrayList<>();

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studyCard")
    private List<Word> words = new ArrayList<>();

    // 양방향 연관관계
    @OneToOne(mappedBy = "studyCard")
    private ReviewCard reviewCard;

    @Builder
    public StudyCard(String koreanTitle, String russianTitle, String koreanDescription, String russianDescription) {
        this.koreanTitle = koreanTitle;
        this.russianTitle = russianTitle;
        this.koreanDescription = koreanDescription;
        this.russianDescription = russianDescription;
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static StudyCard buildStudyCard(String koreanTitle, String russianTitle, String koreanDescription, String russianDescription) {
        return StudyCard.builder()
                .koreanTitle(koreanTitle)
                .russianTitle(russianTitle)
                .koreanDescription(koreanDescription)
                .russianDescription(russianDescription)
                .build();
    }

    // StudyCard 1 <-> ReviewCard 1
    // 양방향 연관관계 편의 메서드
    public void assignReviewCard(ReviewCard reviewCard) {
        if (this.reviewCard != null) {
            this.reviewCard.assignStudyCard(null);
        }

        this.reviewCard = reviewCard;

        if (reviewCard != null && reviewCard.getStudyCard() != this) {
            reviewCard.assignStudyCard(this);
        }
    }

    // StudyCard 1 <-> Dialogue N
    // 양방향 연관관계 편의 메서드
    public void addDialogue(Dialogue dialogue) {
        this.dialogues.add(dialogue);

        if (dialogue.getStudyCard() != this) {
            dialogue.assignStudyCard(this);
        }
    }

    // StudyCard 1 <-> Word N
    // 양방향 연관관계 편의 메서드
    public void addWord(Word word) {
        this.words.add(word);

        if (word.getStudyCard() != this) {
            word.assignStudyCard(this);
        }
    }
}