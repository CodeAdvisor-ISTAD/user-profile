package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/v1/edit_user_profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    // create user profile
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserProfileResponse createUserProfile(@RequestBody UserProfileCreateRequest editUserProfileCreateRequest) {
        return userProfileService.createUser(editUserProfileCreateRequest);
    }

    // search user by username
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{username}")
    UserProfileResponse searchUserByUsername(@Valid @PathVariable String username) {
        return userProfileService.findUserByName(username);
    }

    // get all user
    @GetMapping
    List<UserProfileResponse> getAllUserProfile() {
        return userProfileService.findAllUser();
    }

    // update user profile
    @PatchMapping("/{username}")
    UserProfileResponse updateUserProfile(@Valid @PathVariable String username, @RequestBody UserProfileUpdateRequest userProfileUpdateRequest) {
        return userProfileService.updateUserProfile(username, userProfileUpdateRequest);
    }

    // disable user profile
    @PatchMapping("/{username}/disable")
    void disableUserProfile(@Valid @PathVariable("username") String username) {
        userProfileService.disableUserProfile(username);
    }

    // enable user profile
    @PatchMapping("/{username}/enable")
    void enableUserProfile(@Valid @PathVariable("username") String username) {
        userProfileService.enableUserProfile(username);
    }

    // delete user profile
    @DeleteMapping("/{username}")
    void deleteUserProfile(@Valid @PathVariable String username) {
        userProfileService.deleteUserProfile(username);
    }

}
