package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.domain.UserProfile;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import istad.codeadvisor.userservice.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    // create kafka topic
    private static final String USER_TOPIC = "user-service-topic";
    private final KafkaTemplate<String, UserProfile> kafkaTemplate;

    // create user
    @Override
    public UserProfileResponse createUser(UserProfileCreateRequest userProfileCreateRequest) {
        // validate user
        if (userProfileRepository.existsByUsername(userProfileCreateRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This user is already exist.");
        }
        UserProfile userProfile = userProfileMapper.fromUserProfileCreateRequest(userProfileCreateRequest);
        userProfile.setIsDeleted(false);
        userProfileRepository.save(userProfile);
        kafkaTemplate.send(USER_TOPIC, userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    // search user by name
    @Override
    public UserProfileResponse findUserByName(String username) {
        UserProfile editUserProfile = userProfileRepository.findByUsername(username)
                .filter(user -> !user.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this user."));
        return userProfileMapper.toUserProfileResponse(editUserProfile);
    }

    // get all user
    @Override
    public List<UserProfileResponse> findAllUser() {
        List<UserProfile> editUserProfiles = userProfileRepository.findAll();
        return userProfileMapper.toUserProfileResponseList(editUserProfiles);
    }

    //update user
    @Override
    public UserProfileResponse updateUserProfile(String username, UserProfileUpdateRequest userProfileUpdateRequest) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        userProfileMapper.fromUserProfileUpdateRequest(userProfileUpdateRequest, userProfile);
        userProfileRepository.save(userProfile);
        kafkaTemplate.send(USER_TOPIC, userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    // disable user profile
    @Override
    public void disableUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        userProfile.setIsDeleted(true);
        kafkaTemplate.send(USER_TOPIC, userProfile);
        userProfileRepository.save(userProfile);
    }

    // enable user profile
    @Override
    public void enableUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        userProfile.setIsDeleted(false);
        kafkaTemplate.send(USER_TOPIC, userProfile);
        userProfileRepository.save(userProfile);
    }

    // delete
    @Override
    public void deleteUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        kafkaTemplate.send(USER_TOPIC, userProfile);
        userProfileRepository.delete(userProfile);
    }
}
