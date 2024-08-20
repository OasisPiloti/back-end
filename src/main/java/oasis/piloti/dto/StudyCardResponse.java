package oasis.piloti.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oasis.piloti.entity.Dialogue;
import oasis.piloti.entity.StudyCard;
import oasis.piloti.entity.Word;

import java.util.List;
import java.util.stream.Collectors;

public class StudyCardResponse {

    @Getter
    @NoArgsConstructor
    public static class SimpleInfoDTO {

        private String koreanTitle;
        private String russianTitle;
        private String koreanDescription;
        private String russianDescription;

        @Builder
        public SimpleInfoDTO(String koreanTitle, String russianTitle, String koreanDescription, String russianDescription) {
            this.koreanTitle = koreanTitle;
            this.russianTitle = russianTitle;
            this.koreanDescription = koreanDescription;
            this.russianDescription = russianDescription;
        }

        public static SimpleInfoDTO fromEntity(StudyCard studyCard) {
            return SimpleInfoDTO.builder()
                    .koreanTitle(studyCard.getKoreanTitle())
                    .russianTitle(studyCard.getRussianTitle())
                    .koreanDescription(studyCard.getKoreanDescription())
                    .russianDescription(studyCard.getRussianDescription())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class InfoDTO {
        private String koreanTitle;
        private String russianTitle;
        private String koreanDescription;
        private String russianDescription;
        private List<WordDTO> words;
        private List<DialogueDTO> dialogues;

        @Builder
        public InfoDTO(String koreanTitle, String russianTitle, String koreanDescription, String russianDescription, List<WordDTO> words, List<DialogueDTO> dialogues) {
            this.koreanTitle = koreanTitle;
            this.russianTitle = russianTitle;
            this.koreanDescription = koreanDescription;
            this.russianDescription = russianDescription;
            this.words = words;
            this.dialogues = dialogues;
        }

        public static InfoDTO fromEntity(StudyCard studyCard) {
            return InfoDTO.builder()
                    .koreanTitle(studyCard.getKoreanTitle())
                    .russianTitle(studyCard.getRussianTitle())
                    .koreanDescription(studyCard.getKoreanDescription())
                    .russianDescription(studyCard.getRussianDescription())
                    .words(studyCard.getWords().stream()
                            .map(WordDTO::fromEntity)
                            .collect(Collectors.toList()))
                    .dialogues(studyCard.getDialogues().stream()
                            .map(DialogueDTO::fromEntity)
                            .collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class WordDTO {
        private String koreanWord;
        private String russianWord;
        private String audioUrl;

        @Builder
        public WordDTO(String koreanWord, String russianWord, String audioUrl) {
            this.koreanWord = koreanWord;
            this.russianWord = russianWord;
            this.audioUrl = audioUrl;
        }

        public static WordDTO fromEntity(Word word) {
            return WordDTO.builder()
                    .koreanWord(word.getKoreanWord())
                    .russianWord(word.getRussianWord())
                    .audioUrl(word.getAudioUrl())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DialogueDTO {
        private String koreanSentence;
        private String russianSentence;
        private String audioUrl;

        @Builder
        public DialogueDTO(String koreanSentence, String russianSentence, String audioUrl) {
            this.koreanSentence = koreanSentence;
            this.russianSentence = russianSentence;
            this.audioUrl = audioUrl;
        }

        public static DialogueDTO fromEntity(Dialogue dialogue) {
            return DialogueDTO.builder()
                    .koreanSentence(dialogue.getKoreanSentence())
                    .russianSentence(dialogue.getRussianSentence())
                    .audioUrl(dialogue.getAudioUrl())
                    .build();
        }
    }
}
