package istad.codeadvisor.userservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "edit_user_profiles")
public class EditUserProfile {
    @Id
    private String id;
//    private String familyName;
//    private String givenName;
    private String fullName;
    private String username;
    private String gender;
    private String phoneNumber;
    private String bio;
    private String workPlace;
    private String pob;
    private String school;
    private String jobPosition;
    private String dob;
    private String profileImage;         //  user's profile picture.
    private Boolean isDeleted;
    private String coverColor;
}
