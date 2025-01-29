package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.domain.UserProfile;
import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelRepository;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import istad.codeadvisor.userservice.mapper.AchievementMapper;
import istad.codeadvisor.userservice.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final AchievementLevelRepository achievementLevelRepository;
    private final UserProfileMapper userProfileMapper;
    private final AchievementMapper achievementMapper;
    // create kafka topic
    private static final String USER_TOPIC = "user-updated-event-topic";
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
        // Map UserProfile to AchievementLevel using AchievementMapper
        AchievementLevel achievementLevel = achievementMapper.fromUserProfile(userProfile);
        achievementLevel.setId(UUID.randomUUID().toString()); // Set ID if not auto-generated
//        achievementLevel.setCurrentLevel(achievementLevel.getCurrentLevel());
        achievementLevel.setIsDeleted(false);
        achievementLevel.setIsPublish(true);
        achievementLevelRepository.save(achievementLevel);
//        achievementLevelRepository.save(achievementLevel);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    // search user by name
    @Override
    public UserProfileResponse findUserByName(String username) {
        log.info("username: {}", username);
        UserProfile userProfile = userProfileRepository.findByUsernameAndIsDeleted(username,false)
//                .filter(user -> !user.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this user."));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    // get all user
    @Override
    public List<UserProfileResponse> findAllUser() {
        List<UserProfile> editUserProfiles = userProfileRepository.findAll();
        return userProfileMapper.toUserProfileResponseList(editUserProfiles);
    }

    //update user
    @Override
    public UserProfileResponse updateUserProfile( UserProfileUpdateRequest userProfileUpdateRequest, Jwt jwt) {

        String username = jwt.getClaimAsString("username");

        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));

        userProfile.setFullName(userProfileUpdateRequest.fullName());
        userProfile.setGender(userProfileUpdateRequest.gender());
        userProfile.setBio(userProfileUpdateRequest.bio());
        userProfile.setPhoneNumber(userProfileUpdateRequest.phoneNumber());
        userProfile.setJobPosition(userProfileUpdateRequest.jobPosition());
        userProfile.setSchool(userProfileUpdateRequest.school());
        userProfile.setWorkPlace(userProfileUpdateRequest.workPlace());
        userProfile.setDob(userProfileUpdateRequest.dob());
        userProfile.setPob(userProfileUpdateRequest.pob());
        userProfile.setCoverColor(userProfileUpdateRequest.coverColor());

        userProfileRepository.save(userProfile);
        log.info("userProfile: {}", userProfile);
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
        userProfileRepository.save(userProfile);

    }

    // enable user profile
    @Override
    public void enableUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        userProfile.setIsDeleted(false);

        userProfileRepository.save(userProfile);
    }

    // delete
    @Override
    public void deleteUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        AchievementLevel achievementLevel = achievementLevelRepository.findByUserId(userProfile.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We dit not find this user."));
        achievementLevelRepository.delete(achievementLevel);
        userProfileRepository.delete(userProfile);
    }

    @Override
    @Transactional
    public void updateUserProfileFromIdentity(String uuid, String username, String email, String fullName, String profileImage) {
        UserProfile userProfile = new UserProfile();
        // Update the UserProfile details
        userProfile.setUsername(username);
        userProfile.setAuthorUuid(uuid);
        userProfile.setEmail(email);
        userProfile.setFullName(fullName);
        userProfile.setProfileImage(profileImage);
        userProfile.setIsDeleted(false);
        userProfileRepository.save(userProfile);
        // Map UserProfile to AchievementLevel using AchievementMapper
        AchievementLevel achievementLevel = achievementMapper.fromUserProfile(userProfile);
        achievementLevel.setId(UUID.randomUUID().toString());
        // Set the user ID for AchievementLevel
        achievementLevel.setUserId(userProfile.getId());
        achievementLevel.setIsPublish(true);
        achievementLevel.setIsDeleted(false);
        // Save the updated AchievementLevel
        achievementLevelRepository.save(achievementLevel);
        // Send the updated UserProfile to Kafka
    }

    @Override
    public UserProfileResponse findUserByUuid(String uuid) {
        UserProfile userProfile = userProfileRepository.findByAuthorUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this user."));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Override
    public Long countAllUser() {
        return userProfileRepository.count();
    }
}
