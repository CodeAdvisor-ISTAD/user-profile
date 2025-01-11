package istad.codeadvisor.userservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user_profiles")
public class UserProfile {
    @Id
    private String id;
//    private String familyName;
//    private String givenName;
    private String authorUuid;
    private String fullName;
    private String username;
    private String gender;
    private String phoneNumber;
    private String email;
    private String bio;
    private String workPlace;
    private String pob;
    private String school;
    private String jobPosition;
    private String dob;
    private String profileImage;
    private Boolean isDeleted;
    private String coverColor;
    private AchievementLevel achievementLevel;
}
