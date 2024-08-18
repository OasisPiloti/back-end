package oasis.piloti.Dictionary.controller;

import oasis.piloti.API.ApiBuilder;
import oasis.piloti.Dictionary.DTO.DictionaryDTO;
import oasis.piloti.Dictionary.Service.DictionaryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class DictionaryController {

    @GetMapping("/Dictionary")
    public ResponseEntity<?> Dictionary(@RequestParam("word") String word) throws IOException {
        if(word.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(ApiBuilder.success("단어를 찾을 수 없습니다"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiBuilder.success(DictionaryService.GetMeaning(word)));
    }

}
