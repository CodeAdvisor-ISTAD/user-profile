package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserProfileService {

    // create user
    UserProfileResponse createUser(UserProfileCreateRequest userProfileCreateRequest);

    // search user by name
    UserProfileResponse findUserByName(String username);

    // get all user
    List<UserProfileResponse> findAllUser();
    // update user profile
    UserProfileResponse updateUserProfile( UserProfileUpdateRequest userProfileUpdateRequest, Jwt jwt);
    // disable user by username
    void disableUserProfile(String username);
    // enable user by username
    void  enableUserProfile(String username);
    // delete user profile
    void deleteUserProfile(String username);
    // get user profile from identity service
    void updateUserProfileFromIdentity(String uuid, String username, String email, String fullName, String profileImage);

    UserProfileResponse findUserByUuid(String uuid);

    Long countAllUser();

}
