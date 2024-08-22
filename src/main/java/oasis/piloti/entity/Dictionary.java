package oasis.piloti.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "dictionary")
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long lastBuildDate;
    private String link;
    private int num;
    private int start;
    private String title;
    private int total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_response_id")
    private List<Item> items;

    @Getter
    @Setter
    @Builder
    @Entity
    @Table(name = "item")
    public static class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer targetCode;
        private String word;
        private String origin;
        private String pronunciation;
        private String pos;
        private String link;
        private String translation;
        private String transWord;
        private String transDfn;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "item_id")
        private List<Sense> senses;
    }

    @Getter
    @Setter
    @Builder
    @Entity
    @Table(name = "sense")
    public static class Sense {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int senseOrder;
        private String definition;
    }

    @Getter
    @Setter
    @Builder
    @Entity
    @Table(name = "word")
    public static class Word {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String word;
        private String mean;

        // 정적 팩토리 메서드
        public static Word of(String word, String mean) {
            return Word.builder()
                    .word(word)
                    .mean(mean)
                    .build();
        }
    }
}
