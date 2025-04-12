package stdev.domain.openai.presentation.dto.request;

import lombok.Data;

public record ContentGenerationRequest(
        String topic
) {

}