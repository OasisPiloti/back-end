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
    private String title; // 제목

    @Column(nullable = false)
    private String description; // 설명

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studyCard")
    private List<Dialogue> dialogues = new ArrayList<>();

    // 양방향 연관관계
    @OneToOne(mappedBy = "studyCard")
    private ReviewCard reviewCard;

    @Builder
    private StudyCard(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static StudyCard buildStudyCard(String title, String description) {
        return StudyCard.builder()
                .title(title)
                .description(description)
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
}
