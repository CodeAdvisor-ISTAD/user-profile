package istad.codeadvisor.userservice.feature.edituserprofile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record EditUserProfileCreateRequest (
//    @NotBlank(message = "First name is required")
//    String familyName,
//    @NotBlank(message = "Last name is required")
//    String givenName,
    @NotBlank(message = "Username is required")
    String username,
    @NotBlank(message = "Full name is required")
    String fullName,
    @Size(max = 6, message = "Gender must not exceed 6 characters")
    String gender,
    @Size(max = 500, message = "Bio must not exceed 500 characters")
    String bio,
    String phoneNumber,
    String jobPosition,
    String school,
    String workPlace,
    String profileImage,
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format YYYY-MM-DD")
    String dob,
    String pob,

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    String phone,
    @URL(message = "Invalid URL format")
    String coverColor

) {
}
