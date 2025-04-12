package stdev.domain.openai.presentation.dto.response;

public record LuckyGenerationResponse(
        String text

) {

    public static LuckyGenerationResponse of(String text) {
        return new LuckyGenerationResponse(text);
    }
}
