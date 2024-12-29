package istad.codeadvisor.userservice.additional;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
public record ContentCreate(

    String userId,
    String contentId,
    String reactionType
) {

}
