package istad.codeadvisor.userservice.feature.edituserprofile.dto;

public record EditUserProfileResponse (
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
