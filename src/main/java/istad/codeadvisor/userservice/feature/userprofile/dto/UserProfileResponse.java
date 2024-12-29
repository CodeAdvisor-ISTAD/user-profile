package istad.codeadvisor.userservice.feature.userprofile.dto;

public record UserProfileResponse (
        String id,
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
        String coverColor
) {
}
