package oasis.piloti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EtriApiResponse {

    @Getter
    @NoArgsConstructor
    public static class PronunciationEvaluationDTO {

        @JsonProperty("result")
        private int result;


        @JsonProperty("return_object")
        private EvaluationResultDTO returnObject;

        @Builder
        public PronunciationEvaluationDTO(int result, EvaluationResultDTO returnObject) {
            this.result = result;
            this.returnObject = returnObject;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class EvaluationResultDTO {

        @JsonProperty("recognized")
        private String recognized;

        @JsonProperty("score")
        private String score;

        @Builder
        public EvaluationResultDTO(String recognized, String score) {
            this.recognized = recognized;
            this.score = score;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorResponseDTO {

        @JsonProperty("result")
        private int result;

        @JsonProperty("reason")
        private String reason;

        @Builder
        public ErrorResponseDTO(int result, String reason) {
            this.result = result;
            this.reason = reason;
        }
    }
}