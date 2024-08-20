package oasis.piloti.service;

import lombok.RequiredArgsConstructor;
import oasis.piloti.dto.EtriApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PronunciationService {

    private final EtriApiService etriApiService;

    public EtriApiResponse.PronunciationEvaluationDTO evaluatePronunciation(MultipartFile file, String script) throws Exception {

        // 파일을 바이트 배열로 변환
        byte[] audioData = file.getBytes();

        // ETRI 발음 평가 API 호출
        return etriApiService.requestPronunciationEvaluation(script, audioData);
    }
}
