//package oasis.piloti.initializer;
//
//import lombok.RequiredArgsConstructor;
//import oasis.piloti.entity.Dialogue;
//import oasis.piloti.entity.StudyCard;
//import oasis.piloti.entity.Word;
//import oasis.piloti.repository.DialogueRepository;
//import oasis.piloti.repository.StudyCardRepository;
//import oasis.piloti.repository.WordRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final StudyCardRepository studyCardRepository;
//    private final WordRepository wordRepository;
//    private final DialogueRepository dialogueRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        for (int i = 1; i <= 3; i++) {
//            StudyCard studyCard = StudyCard.buildStudyCard(
//                    "한국어 제목 " + i,
//                    "러시아어 제목 " + i,
//                    "한국어 설명 " + i,
//                    "러시아어 설명 " + i
//            );
//
//            studyCardRepository.save(studyCard);
//
//            // 단어 예시 추가
//            Word word1 = Word.buildWord("한국어 단어 " + i, "러시아어 단어 " + i, "https://example.com/audio" + i + ".mp3", studyCard);
//            Word word2 = Word.buildWord("한국어 단어 " + (i+1), "러시아어 단어 " + (i+1), "https://example.com/audio" + (i+1) + ".mp3", studyCard);
//            wordRepository.save(word1);
//            wordRepository.save(word2);
//
//
//            // 문장 예시 추가
//            Dialogue dialogue1 = Dialogue.buildDialogue("한국어 문장 " + i, "러시아어 문장 " + i, "https://example.com/audio" + i + ".mp3", studyCard);
//            Dialogue dialogue2 = Dialogue.buildDialogue("한국어 문장 " + (i+1), "러시아어 문장 " + (i+1), "https://example.com/audio" + (i+1) + ".mp3", studyCard);
//            dialogueRepository.save(dialogue1);
//            dialogueRepository.save(dialogue2);
//        }
//    }
//}
