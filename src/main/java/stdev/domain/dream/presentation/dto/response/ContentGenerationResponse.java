package stdev.domain.dream.presentation.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentGenerationResponse {
    private String text;
    private String imageUrl;
}