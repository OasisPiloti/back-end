package oasis.piloti.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import oasis.piloti.dto.DictionaryDTO;
import oasis.piloti.dto.DictionaryResponseDTO;
import oasis.piloti.repository.DictionaryRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Transactional
@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }


    @Transactional
    public static String GetMeaning(String word) throws IOException {
        String baseUrl = "https://krdict.korean.go.kr/api/search";
        String encodeWord = URLEncoder.encode(word, StandardCharsets.UTF_8);

       DictionaryDTO.DictionaryRequestDTO req = new DictionaryDTO.DictionaryRequestDTO("EFD6F03BC9070B813F9A16E59DC3E855", encodeWord);
       StringBuilder result = new StringBuilder();

        URL url = new URL(baseUrl + req.getParameter());
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String returnLine;
        while ((returnLine = bufferedReader.readLine()) != null) {
            result.append(returnLine);
        }

        // JSON으로 변환
        JSONObject jsonObject = org.json.XML.toJSONObject(result.toString());

        ObjectMapper mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


        DictionaryResponseDTO res = mapper.readValue(jsonObject.toString(), DictionaryResponseDTO.class);
        System.out.println(res);

        if (res.getChannel().getItem().isEmpty()) {
            return ("단어를 찾을 수 없습니다");
        }

        // DB에 뜻 저장
        //TO DO: 리스트로 만들어서 전부 출력하기
        String mean = res.getChannel().getItem().get(0).getSense().get(0).getDefinition();
        //String translation = res.getChannel().getItem().get(0).getTrans_word();
        //dictionaryRepository.save(Word.of(word, mean));

        return mean;
    }
}
