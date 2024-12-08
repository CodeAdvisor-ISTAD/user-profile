package istad.codeadvisor.userservice.feature.edituserprofile;

import istad.codeadvisor.userservice.domain.EditUserProfile;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileResponse;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileUpdateRequest;
import istad.codeadvisor.userservice.mapper.EditUserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditUserProfileServiceImpl implements EditUserProfileService{
    private final EditUserProfileRepository editUserProfileRepository;
    private final EditUserProfileMapper editUserProfileMapper;

    // create user
    @Override
    public EditUserProfileResponse createUser(EditUserProfileCreateRequest editUserProfileCreateRequest) {
        // validate user
        if (editUserProfileRepository.existsByUsername(editUserProfileCreateRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This user is already exist.");
        }
        EditUserProfile editUserProfile = editUserProfileMapper.fromEditUserProfileCreateRequest(editUserProfileCreateRequest);
        editUserProfile.setIsDeleted(false);
        editUserProfileRepository.save(editUserProfile);
        return editUserProfileMapper.toEditUserProfileResponse(editUserProfile);
    }

    // search user by name
    @Override
    public EditUserProfileResponse findUserByName(String username) {
        EditUserProfile editUserProfile = editUserProfileRepository.findByUsername(username)
                .filter(user -> !user.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this user."));
        return editUserProfileMapper.toEditUserProfileResponse(editUserProfile);
    }

    // get all user
    @Override
    public List<EditUserProfileResponse> findAllUser() {
        List<EditUserProfile> editUserProfiles = editUserProfileRepository.findAll();
        return editUserProfileMapper.toEditUserProfileResponseList(editUserProfiles);
    }

    //update user
    @Override
    public EditUserProfileResponse updateUserProfile(String username, EditUserProfileUpdateRequest editUserProfileUpdateRequest) {
        EditUserProfile editUserProfile = editUserProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        editUserProfileMapper.fromEditUserProfileUpdateRequest(editUserProfileUpdateRequest, editUserProfile);
        editUserProfileRepository.save(editUserProfile);
        return editUserProfileMapper.toEditUserProfileResponse(editUserProfile);
    }

    // disable user profile
    @Override
    public void disableUserProfile(String username) {
        EditUserProfile editUserProfile = editUserProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        editUserProfile.setIsDeleted(true);
        editUserProfileRepository.save(editUserProfile);
    }

    // enable user profile
    @Override
    public void enableUserProfile(String username) {
        EditUserProfile editUserProfile = editUserProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        editUserProfile.setIsDeleted(false);
        editUserProfileRepository.save(editUserProfile);
    }

    // delete
    @Override
    public void deleteUserProfile(String username) {
        EditUserProfile editUserProfile = editUserProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        editUserProfileRepository.delete(editUserProfile);
    }
}
