package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Dialogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String koreanText; // 한국 문장

    @Column(nullable = false)
    private String russianText; // 러시아 문장

    @Column(nullable = false)
    private String audioUrl; // 음성 주소

    // 연관관계 주인
    // 양방향 연관관계
    @ManyToOne
    @JoinColumn(name = "study_card_id")
    private StudyCard studyCard;

    // 양방향 연관관계
    @OneToOne(mappedBy = "dialogue")
    private IncorrectDialogue incorrectDialogue;

    @Builder
    private Dialogue(String koreanText, String russianText, String audioUrl, StudyCard studyCard) {
        this.koreanText = koreanText;
        this.russianText = russianText;
        this.audioUrl = audioUrl;
        assignStudyCard(studyCard);
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static Dialogue buildDialogue(String koreanText, String russianText, String audioUrl, StudyCard studyCard) {
        return Dialogue.builder()
                .koreanText(koreanText)
                .russianText(russianText)
                .audioUrl(audioUrl)
                .studyCard(studyCard)
                .build();
    }

    // Dialogue N <-> StudyCard 1
    // 양방향 연관관계 편의 메서드
    public void assignStudyCard(StudyCard studyCard) {
        if (this.studyCard != null) {
            this.studyCard.getDialogues().remove(this);
        }

        this.studyCard = studyCard;

        if (!studyCard.getDialogues().contains(this)) {
            studyCard.addDialogue(this);
        }
    }

    // Dialogue 1 <-> IncorrectDialogue 1
    // 양방향 연관관계 편의 메서드
    public void assignIncorrectDialogue(IncorrectDialogue incorrectDialogue) {
        if (this.incorrectDialogue != null) {
            this.incorrectDialogue.assignDialogue(null);
        }

        this.incorrectDialogue = incorrectDialogue;

        if (incorrectDialogue != null && incorrectDialogue.getDialogue() != this) {
            incorrectDialogue.assignDialogue(this);
        }
    }
}
