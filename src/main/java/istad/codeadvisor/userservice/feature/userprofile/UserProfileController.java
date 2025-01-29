package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.feature.fileupload.FileUploadService;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileCreateRequest;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileImageResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user_profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final FileUploadService fileUploadService;

    @PatchMapping("/upload")
    public ProfileImageResponse uploadProfileImage(
            @RequestBody ProfileCreateRequest request, // Ensure this is correct
            @AuthenticationPrincipal Jwt jwt
    ) {
        String username = jwt.getClaimAsString("username");
        return fileUploadService.uploadProfileImage(request, username);
    }

    // get user profile by me
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public UserProfileResponse getMe(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("username");
        return userProfileService.findUserByName(username);
    }

    // create user profile
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserProfileResponse createUserProfile(@RequestBody UserProfileCreateRequest editUserProfileCreateRequest) {
        return userProfileService.createUser(editUserProfileCreateRequest);
    }

//     search user by username
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{username}")
    UserProfileResponse searchUserByUsername( @Valid @PathVariable String username) {
        return userProfileService.findUserByName(username);
    }


    // get all user
    @GetMapping
    List<UserProfileResponse> getAllUserProfile() {
        return userProfileService.findAllUser();
    }

    // update user profile
//    @PreAuthorize("isAuthenticated()")
    @PatchMapping()
    UserProfileResponse updateUserProfile( @Valid @RequestBody UserProfileUpdateRequest userProfileUpdateRequest, @AuthenticationPrincipal Jwt jwt) {
        log.info("jwt in update profile : {}", jwt);
        return userProfileService.updateUserProfile(userProfileUpdateRequest, jwt);
    }

    // update profile images

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

    // get user profile
    @GetMapping("/{uuid}/profile")
    UserProfileResponse getUserProfile(@Valid @PathVariable String uuid) {
        return userProfileService.findUserByUuid(uuid);
    }

    // get user profile by username
    @GetMapping("/total-users")
    public long getTotalUsers() {
        return userProfileService.countAllUser();
    }

}
