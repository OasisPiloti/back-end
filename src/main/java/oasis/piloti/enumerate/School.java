package oasis.piloti.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum School {

    ELEMENTARY("elementary"),
    MIDDLE("middle"),
    HIGH("high");

    private final String value;
}
