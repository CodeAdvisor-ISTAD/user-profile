package istad.codeadvisor.userservice.feature.edituserprofile;

import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileResponse;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/v1/edit_user_profiles")
public class EditUserProfileController {
    private final EditUserProfileService editUserProfileService;

    // create user profile
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    EditUserProfileResponse createUserProfile(@RequestBody EditUserProfileCreateRequest editUserProfileCreateRequest) {
        return editUserProfileService.createUser(editUserProfileCreateRequest);
    }

    // search user by username
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{username}")
    EditUserProfileResponse searchUserByUsername(@Valid @PathVariable String username) {
        return editUserProfileService.findUserByName(username);
    }

    // get all user
    @GetMapping
    List<EditUserProfileResponse> getAllUserProfile() {
        return editUserProfileService.findAllUser();
    }

    // update user profile
    @PatchMapping("/{username}")
    EditUserProfileResponse updateUserProfile(@Valid @PathVariable String username, @RequestBody EditUserProfileUpdateRequest editUserProfileUpdateRequest) {
        return editUserProfileService.updateUserProfile(username, editUserProfileUpdateRequest);
    }

    // disable user profile
    @PatchMapping("/{username}/disable")
    void disableUserProfile(@Valid @PathVariable("username") String username) {
        editUserProfileService.disableUserProfile(username);
    }

    // enable user profile
    @PatchMapping("/{username}/enable")
    void enableUserProfile(@Valid @PathVariable("username") String username) {
        editUserProfileService.enableUserProfile(username);
    }

    // delete user profile
    @DeleteMapping("/{username}")
    void deleteUserProfile(@Valid @PathVariable String username) {
        editUserProfileService.deleteUserProfile(username);
    }

}
