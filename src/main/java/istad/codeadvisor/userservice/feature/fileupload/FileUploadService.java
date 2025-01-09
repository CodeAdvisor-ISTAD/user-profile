package istad.codeadvisor.userservice.feature.fileupload;

import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileCreateRequest;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileImageResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
//    ProfileImageResponse updateProfileImage(UpdateProfileImageRequest updateProfileImageRequest, MultipartFile file, Jwt jwt);
//    ProfileImageResponse uploadProfileImage(String imageUrl, String username);


    ProfileImageResponse uploadProfileImage(ProfileCreateRequest request, String username);

    ProfileImageResponse getProfileImage(String id);
    void deleteProfileImage(String id);
}
