package istad.codeadvisor.userservice.feature.userprofile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

public record UserProfileUpdateRequest (
//        @NotBlank(message = "First name is required")
//        String familyName,
//        @NotBlank(message = "Last name is required")
//        String givenName,
//        @NotBlank(message = "Username is required")
//        String username,

        String fullName,
        String gender,
        String bio,
        String phoneNumber,
        String jobPosition,
        String school,
        String workPlace,
        String dob,
        String pob,
        String coverColor
) {

}
