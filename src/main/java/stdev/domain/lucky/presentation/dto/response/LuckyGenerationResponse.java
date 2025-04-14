package stdev.domain.lucky.presentation.dto.response;

public record LuckyGenerationResponse(
        String text

) {

    public static LuckyGenerationResponse of(String text) {
        return new LuckyGenerationResponse(text);
    }
}
