package istad.codeadvisor.userservice.feature.edituserprofile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record EditUserProfileUpdateRequest (
//        @NotBlank(message = "First name is required")
//        String familyName,
//        @NotBlank(message = "Last name is required")
//        String givenName,
//        @NotBlank(message = "Username is required")
//        String username,
        @NotBlank(message = "Full name is required")
        String fullName,
        @Size(max = 6, message = "Gender must not exceed 6 characters")
        String gender,
        @Size(max = 500, message = "Bio must not exceed 500 characters")
        String bio,
        @Size(max = 15, message = "Phone number must not exceed 15 characters")
        String phoneNumber,
        @Size(max = 50, message = "Job position must not exceed 50 characters")
        String jobPosition,
        String school,
        String workPlace,
        String profileImage,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format YYYY-MM-DD")
        String dob,
        String pob,
        @URL(message = "Invalid URL format")
        String coverColor
) {
}
