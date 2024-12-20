package istad.codeadvisor.userservice.additional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private String familyName;
    private String givenName;
    private String username;
    private String gender;
    private String phoneNumber;
    private String bio;
    private String workPlace;
    private String pob;
    private String school;
    private String jobPosition;
    private String dob;
}
