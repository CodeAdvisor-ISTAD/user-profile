package istad.codeadvisor.userservice.feature.fileupload;

import istad.codeadvisor.userservice.domain.UserProfile;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileCreateRequest;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileImageResponse;
import istad.codeadvisor.userservice.feature.userprofile.UserProfileRepository;
import istad.codeadvisor.userservice.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService{
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private static final String USER_TOPIC = "user-updated-event-topic";
    private final KafkaTemplate<String, UserProfile> kafkaTemplate;


//    @Override
//    public ProfileImageResponse uploadProfileImage(String imageUrl, String username) {
//        // Log the imageUrl (for debugging)
//        System.out.println("Received imageUrl: " + imageUrl);
//
//        // Find the user profile by username
//        UserProfile userProfile = userProfileRepository.findByUsername(username)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//
//        // Log the current profile image (for debugging)
//        System.out.println("Current profile image: " + userProfile.getProfileImage());
//
//        // Update the profileImage field with the imageUrl
//        userProfile.setProfileImage(imageUrl);
//
//        // Log the updated profile image (for debugging)
//        System.out.println("Updated profile image: " + userProfile.getProfileImage());
//
//        // Save the updated user profile
//        try {
//            userProfileRepository.save(userProfile);
//            System.out.println("Profile image updated successfully for user: " + username);
//        } catch (Exception e) {
//            System.err.println("Failed to save user profile: " + e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save user profile", e);
//        }
//
//        // Return the response with the image URL and username
//        return new ProfileImageResponse(imageUrl, username);
//    }

    @Override
    public ProfileImageResponse uploadProfileImage(ProfileCreateRequest request, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Log the received imageUrl
        System.out.println("Received imageUrl: " + request.imageUrl());

        // Ensure the imageUrl is correctly read from the request
        String imageUrl = request.imageUrl();
        if (imageUrl == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image URL is required");
        }

        userProfile.setProfileImage(imageUrl); // Set the image URL
        userProfileRepository.save(userProfile);

        kafkaTemplate.send(USER_TOPIC, userProfile); // Send the updated user profile to the Kafka topic

        return new ProfileImageResponse(imageUrl, username);
    }
    // Get the profile image of the user
    @Override
    public ProfileImageResponse getProfileImage(String id) {
        // Find the user profile
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "We did not find this user."));
        // Return the profile image URL
        return userProfileMapper.toProfileImageResponse(userProfile);
    }

    // Delete the profile image of the user
    @Override
    public void deleteProfileImage(String id) {

    }
}
