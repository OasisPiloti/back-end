package oasis.piloti.service;

import lombok.RequiredArgsConstructor;
import oasis.piloti.dto.EtriApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EtriApiService {

    private static final String ETRI_PRONUNCIATION_API_URL = "http://aiopen.etri.re.kr:8000/WiseASR/PronunciationKor/";

    // 환경 변수로 등록한 ETRI API 키
    @Value("${etri.rest.api.key}")
    private String access_key;

    // ETRI API 호출을 위한 클라이언트 모듈
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public EtriApiResponse.PronunciationEvaluationDTO requestPronunciationEvaluation(String script, byte[] audioData)  throws IOException {

        // ETRI API의 URI 가져오기
        URI uri = UriComponentsBuilder.fromHttpUrl(ETRI_PRONUNCIATION_API_URL).build().encode().toUri();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, access_key);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        // 요청 본문 데이터 생성
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        String audioBase64 = Base64.getEncoder().encodeToString(audioData);
        argument.put("language_code", "korean");
        argument.put("script", script);
        argument.put("audio", audioBase64);
        requestBody.put("argument", argument);

        // 요청 엔터티 생성
        HttpEntity<Map<String, Object>> mapHttpEntity = new HttpEntity<>(requestBody, headers);
        

        // ETRI API 호출 및 응답 받기 (String으로 수정)
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                mapHttpEntity,
                String.class // 응답을 String으로 받기
        );

        // 응답 본문 가져오기
        String responseBody = response.getBody();


        // 응답이 null인 경우 예외 처리
        if (response == null) {
            throw new RuntimeException("ETRI API 서버에 문제가 있습니다.");
        }

        // 응답 반환
        return objectMapper.readValue(responseBody, EtriApiResponse.PronunciationEvaluationDTO.class);
    }
}
