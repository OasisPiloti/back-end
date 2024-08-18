package oasis.piloti.Dictionary;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import lombok.extern.slf4j.Slf4j;
import oasis.piloti.API.ApiBuilder;
import oasis.piloti.Dictionary.DTO.TranslateRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import static java.awt.SystemColor.text;

@Slf4j
@Service
public class GoogleTranslate {
    String sourceLanguage = "ru"; // 원본 언어: 러시아어
    String targetLanguage = "ko"; // 목표 언어: 한국어
    private String credentialsPath = "googleDictionApiKEY.json";

    public ResponseEntity<?> getTranslatedText(TranslateRequestDTO translateRequestDto) {
        // ....

        // json 파일 경로
        String jsonPath = credentialsPath;
        // json 파일에서 GoogleCredentials 객체 생성
        try (InputStream serviceAccountStream = new URL(jsonPath).openStream()) {
            GoogleCredentials credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
            // Translate 서비스 생성
            Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
            // 번역 api 코드 추가
            Translation translation = translate.translate(String.valueOf(text), Translate.TranslateOption.sourceLanguage(sourceLanguage),
                    Translate.TranslateOption.targetLanguage(targetLanguage));
            String translatedText = translation.getTranslatedText();
            translatedText = translatedText.replaceAll("&#39;", "\'");
        } catch (IOException e) {
            System.out.println("오류가 있습니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiBuilder.success(translateRequestDto.getMeaning()));
    }
}