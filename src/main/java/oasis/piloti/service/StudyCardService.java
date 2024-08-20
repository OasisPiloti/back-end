package oasis.piloti.service;

import lombok.RequiredArgsConstructor;
import oasis.piloti.dto.StudyCardResponse;
import oasis.piloti.entity.StudyCard;
import oasis.piloti.repository.StudyCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyCardService {

    private final StudyCardRepository studyCardRepository;

    public List<StudyCardResponse.SimpleInfoDTO> getStudyCards() {
        return studyCardRepository.findAll().stream()
                .map(StudyCardResponse.SimpleInfoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public StudyCardResponse.InfoDTO getStudyCard(Long studyCardId) {
        StudyCard studyCard = studyCardRepository.findById(studyCardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 학습 카드를 찾을 수 없습니다. Id: " + studyCardId));
        return StudyCardResponse.InfoDTO.fromEntity(studyCard);
    }
}
