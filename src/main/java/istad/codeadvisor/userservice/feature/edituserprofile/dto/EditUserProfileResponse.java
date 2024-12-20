package istad.codeadvisor.userservice.feature.edituserprofile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record EditUserProfileResponse (
        String id,
        Integer userId,
//        String familyName,
//        String givenName,
        String fullName,
        String username,
        String gender,
        String bio,
        String phoneNumber,
        String jobPosition,
        String school,
        String workPlace,
        String profileImage,
        String dob,
        String pob,
        String phone,
        String coverColor
) {
}
