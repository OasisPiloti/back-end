package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate studyDate; // 공부 날짜

    // 양방향 연관관계
    // 연관관계 주인
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private StudyRecord(LocalDate studyDate, Member member) {
        this.studyDate = studyDate;
        assignMember(member);
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static StudyRecord buildStudyRecord(LocalDate studyDate, Member member) {
        return StudyRecord.builder()
                .studyDate(studyDate)
                .member(member)
                .build();
    }

    // StudyRecord N <-> Member 1
    // 양방향 연관관계 편의 메서드
    public void assignMember(Member member) {
        if (this.member != null) {
            this.member.getStudyRecords().remove(this);
        }

        this.member = member;

        if (!member.getStudyRecords().contains(this)) {
            member.addStudyRecord(this);
        }
    }
}
