package stdev.domain.dreamanalysis.presentation.dto.response;

public record DreamCommentResponse(
        String comment,

        String category
) {
    public static DreamCommentResponse of(String comment, String category){
        return new DreamCommentResponse(comment,category);
    }
}
