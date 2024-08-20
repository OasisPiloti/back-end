package oasis.piloti.controller;

import lombok.RequiredArgsConstructor;
import oasis.piloti.api.ApiResponse;
import oasis.piloti.dto.StudyCardResponse;
import oasis.piloti.service.StudyCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static oasis.piloti.api.ResponseBuilder.*;

@RestController
@RequiredArgsConstructor
public class StudyCardController {

    private final StudyCardService studyCardService;

    @GetMapping("/api/study-cards")
    public ResponseEntity<ApiResponse<List<StudyCardResponse.SimpleInfoDTO>>> getStudyCards() {

        List<StudyCardResponse.SimpleInfoDTO> studyCards = studyCardService.getStudyCards();

        return buildSuccessResponseWithData(studyCards, "학습 카드 리스트 조회를 성공하였습니다.", HttpStatus.OK);
    }

    @GetMapping("/api/study-card/{studyCardId}")
    public ResponseEntity<ApiResponse<StudyCardResponse.InfoDTO>> getStudyCard(@PathVariable Long studyCardId) {

        StudyCardResponse.InfoDTO studyCard = studyCardService.getStudyCard(studyCardId);

        return buildSuccessResponseWithData(studyCard, "학습 카드 조회를 성공하였습니다.", HttpStatus.OK);
    }
}
