package istad.codeadvisor.userservice.additional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;

    private String content; // or content

    private String thumbnail;

    private String keyword;

    private String slug;

//    private List<Tags> tags;
//
//    private CommunityEngagement communityEngagement;

    private Boolean isDeleted;

    private Boolean isDraft;

    @CreatedBy
    @Field("author_id")
    private String authorId;

    @CreatedDate
    @Field("created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate;
}
