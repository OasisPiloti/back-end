package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oasis.piloti.enumerate.Grade;
import oasis.piloti.enumerate.School;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private School school; // 학교

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade; // 학년

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<ReviewCard> reviewCards = new ArrayList<>();

    // 양방향 연관관계
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<StudyRecord> studyRecords = new ArrayList<>();

    @Builder
    private Member(String username, String password, String nickname, String email, School school, Grade grade) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.school = school;
        this.grade = grade;
    }

    // 직접 빌더 패턴의 생성자를 활용하지 말고 해당 메서드를 활용하여 엔티티 생성
    public static Member buildMember(String username, String password, String nickname, String email, School school, Grade grade) {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .school(school)
                .grade(grade)
                .build();
    }

    // Member 1 <-> ReviewCard N
    // 양방향 연관관계 편의 메서드
    public void addReviewCard(ReviewCard reviewCard) {
        this.reviewCards.add(reviewCard);

        if (reviewCard.getMember() != this) {
            reviewCard.assignMember(this);
        }
    }

    // Member 1 <-> StudyRecord N
    // 양방향 연관관계 편의 메서드
    public void addStudyRecord(StudyRecord studyRecord) {
        this.studyRecords.add(studyRecord);

        if (studyRecord.getMember() != this) {
            studyRecord.assignMember(this);
        }
    }
}
