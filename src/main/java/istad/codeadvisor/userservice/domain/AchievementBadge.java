package istad.codeadvisor.userservice.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "achievement_badges")
public class AchievementBadge {
    @Id
    private ObjectId id;
    private Integer userId;
    private String badgeImage;
    private String badgeName;
    private LocalDateTime assignAt;
    private Boolean isPublish;
    private Boolean isDeleted;
}
