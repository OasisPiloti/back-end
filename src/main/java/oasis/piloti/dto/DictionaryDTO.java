package oasis.piloti.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DictionaryDTO {

    @Getter
    @Setter
    public static class DictionaryRequestDTO{
        private String key; //인증키
        private String q;   //검색어
        private String part; //검색대상
        private String translated; //다국어 번역 여부
        private String advanced;  //자세히 찾기 여부
        private String method; //검색 방식
        private Integer target;
        private String trans_lang;


        public DictionaryRequestDTO(String key, String q){
            this.key = key;
            this.q=q;
            this.part = "word";
            this.translated="y";
            this.advanced="y";
            this.method="exact";
            //this.target=4;
            this.trans_lang="10";
        }


        public String getParameter() {
            return "?key=" + this.key +
                    "&q=" + this.q +
                    "&part=" + this.part +
                    "&translated=" + this.translated +
                    "&advanced=" + this.advanced +
                    "&method=" + this.method+
                    "&trans_lang"+this.trans_lang;
                   // "&target=" + this.target;

        }
    }




}
