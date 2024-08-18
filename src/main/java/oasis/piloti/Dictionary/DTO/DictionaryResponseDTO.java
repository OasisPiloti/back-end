package oasis.piloti.Dictionary.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DictionaryResponseDTO {
    private Channel channel;

    @Getter
    @Setter
        public static class Channel {
            private String description;
            private List<Item> item;
            private Long lastBuildDate;
            private String link;
            private int num;
            private int start;
            private String title;
            private int total;


        }

    @Getter
    @Setter
        public static class Item{

            private Integer target_code;
            private String word;
            private String origin;
            private String pronunciation;
            private String pos;
            private String link;
            private List<Sense> sense;
            private String translation;
            private String trans_word;
            private String trans_dfn;


        }

    @Getter
    @Setter
    public static class Sense{
        private int sense_order;
        private String definition;


    }
}
