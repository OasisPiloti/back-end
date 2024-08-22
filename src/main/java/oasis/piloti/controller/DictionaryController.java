package oasis.piloti.controller;

import oasis.piloti.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

import static oasis.piloti.api.ResponseBuilder.buildFailureResponse;
import static oasis.piloti.api.ResponseBuilder.buildSuccessResponseWithData;


@RestController
@RequestMapping("/api")
public class DictionaryController {

    @GetMapping("/Dictionary")
    public ResponseEntity<? extends oasis.piloti.api.ApiResponse<? extends Object>> Dictionary(@RequestParam("word") String word) throws IOException {
        if(word.isBlank()){
            return buildFailureResponse("단어를 찾을 수 없습니다.",HttpStatus.OK);}
        return buildSuccessResponseWithData(DictionaryService.GetMeaning(word),"단어를 성공적으로 찾았습니다.",HttpStatus.OK);
    }

}
