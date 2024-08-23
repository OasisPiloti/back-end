package oasis.piloti.controller;

import lombok.RequiredArgsConstructor;
import oasis.piloti.api.ApiResponse;
import oasis.piloti.dto.EtriApiResponse;
import oasis.piloti.service.PronunciationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static oasis.piloti.api.ResponseBuilder.*;


@RestController
@RequiredArgsConstructor
public class PronunciationController {

    private final PronunciationService pronunciationService;

    @PostMapping("/api/pronunciation/evaluate")
    public ResponseEntity<ApiResponse<Void>> evaluatePronunciation(
            @RequestParam("file") MultipartFile file,
            @RequestParam("script") String script) throws Exception {

        pronunciationService.evaluatePronunciation(file, script);

        return buildSuccessResponseWithoutData("발음이 정확합니다.", HttpStatus.OK);
    }
}
