package oasis.piloti.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oasis.piloti.api.ApiResponse;
import oasis.piloti.service.PronunciationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static oasis.piloti.api.ResponseBuilder.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PronunciationController {

    private final PronunciationService pronunciationService;

    @PostMapping("/api/pronunciation/evaluate")
    public ResponseEntity<ApiResponse<Void>> evaluatePronunciation(
            @RequestParam("file") MultipartFile file,
            @RequestParam("script") String script) throws Exception {

        log.info("API 호출: 발음 평가");

        pronunciationService.evaluatePronunciation(file, script);

        return buildSuccessResponseWithoutData("발음이 정확합니다.", HttpStatus.OK);
    }
}
