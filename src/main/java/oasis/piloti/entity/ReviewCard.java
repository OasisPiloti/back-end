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
public class ReviewCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean reviewed; // 복습여부

    // 연관관계 주인
    // 양방향 연관관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewCard")
    private List<IncorrectDialogue> incorrectDialogues = new ArrayList<>();

    // 연관관계 주인
    // 양방향 연관관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_card_id")
    private StudyCard studyCard;

    @Builder
    private ReviewCard(Boolean reviewed, Member member, StudyCard studyCard) {
        this.reviewed = reviewed;
        assignMember(member);
        assignStudyCard(studyCard);
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static ReviewCard buildReviewCard(Boolean reviewed, Member member, StudyCard studyCard) {
        return ReviewCard.builder()
                .reviewed(reviewed)
                .member(member)
                .studyCard(studyCard)
                .build();
    }

    // ReviewCard N <-> Member 1
    // 양방향 연관관계 편의 메서드
    public void assignMember(Member member) {
        if (this.member != null) {
            this.member.getReviewCards().remove(this);
        }

        this.member = member;

        if (!member.getReviewCards().contains(this)) {
            member.addReviewCard(this);
        }
    }

    // ReviewCard 1 <-> IncorrectDialogue
    // 양방향 연관관계 편의 메서드
    public void addIncorrectDialogue(IncorrectDialogue incorrectDialogue) {
        this.incorrectDialogues.add(incorrectDialogue);

        if (incorrectDialogue.getReviewCard() != this) {
            incorrectDialogue.assignReviewCard(this);
        }
    }

    // ReviewCard 1 <-> StudyCard 1
    // 양방향 연관관계 편의 메서드
    public void assignStudyCard(StudyCard studyCard) {
        if (this.studyCard != null) {
            this.studyCard.assignReviewCard(null);
        }

        this.studyCard = studyCard;

        if (studyCard != null && studyCard.getReviewCard() != this) {
            studyCard.assignReviewCard(this);
        }
    }
}
