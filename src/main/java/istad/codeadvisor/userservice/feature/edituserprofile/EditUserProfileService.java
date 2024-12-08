package istad.codeadvisor.userservice.feature.edituserprofile;

import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileResponse;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileUpdateRequest;

import java.util.List;

public interface EditUserProfileService {
    // create user
    EditUserProfileResponse createUser(EditUserProfileCreateRequest editUserProfileCreateRequest);
    // search user by name
    EditUserProfileResponse findUserByName(String username);
    // get all user
    List<EditUserProfileResponse> findAllUser();
    // update user profile
    EditUserProfileResponse updateUserProfile(String username, EditUserProfileUpdateRequest editUserProfileUpdateRequest);
    // disable user by username
    void disableUserProfile(String username);
    // enable user by username
    void  enableUserProfile(String username);
    // delete user profile
    void deleteUserProfile(String username);

}
