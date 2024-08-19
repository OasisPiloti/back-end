package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class IncorrectDialogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double accuracy;  // 틀린 정확도

    @Column(nullable = false)
    private Boolean corrected;  // 복습 후 맞췄는지 여부

    // 연관관계 주인
    // 양방향 연관관계
    @ManyToOne
    @JoinColumn(name = "review_card_id")
    private ReviewCard reviewCard;

    // 연관관게 주인
    // 양방향 연관관계
    @OneToOne
    @JoinColumn(name = "dialogue_id")
    private Dialogue dialogue;

    @Builder
    public IncorrectDialogue(Double accuracy, Boolean corrected, Dialogue dialogue, ReviewCard reviewCard) {
        this.accuracy = accuracy;
        this.corrected = corrected;
        assignDialogue(dialogue);
        assignReviewCard(reviewCard);
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static IncorrectDialogue buildIncorrectDialogue(Double accuracy, Boolean corrected, Dialogue dialogue, ReviewCard reviewCard) {
        return IncorrectDialogue.builder()
                .accuracy(accuracy)
                .corrected(corrected)
                .dialogue(dialogue)
                .reviewCard(reviewCard)
                .build();
    }

    // IncorrectDialogue 1 <-> Dialogue 1
    // 양방향 연관관계 편의 메서드
    public void assignDialogue(Dialogue dialogue) {
        if (this.dialogue != null) {
            this.dialogue.assignIncorrectDialogue(null);
        }

        this.dialogue = dialogue;

        if (dialogue != null && dialogue.getIncorrectDialogue() != this) {
            dialogue.assignIncorrectDialogue(this);
        }
    }

    // IncorrectDialogue N <-> 1 ReviewCard
    // 양방향 연관관계 편의 메서드
    public void assignReviewCard(ReviewCard reviewCard) {
        if (this.getReviewCard() != null) {
            this.getReviewCard().getIncorrectDialogues().remove(this);
        }

        this.reviewCard = reviewCard;

        if (!reviewCard.getIncorrectDialogues().contains(this)) {
            reviewCard.addIncorrectDialogue(this);
        }
    }
}
