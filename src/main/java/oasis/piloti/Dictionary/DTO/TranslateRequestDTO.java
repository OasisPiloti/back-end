package oasis.piloti.Dictionary.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranslateRequestDTO {
    private String meaning;

    public TranslateRequestDTO(){
        this.meaning=getMeaning();
    }
}
