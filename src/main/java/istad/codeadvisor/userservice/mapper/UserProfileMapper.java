package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.UserProfile;
import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileImageResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileUpdateRequest;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserProfileMapper {
    // create user
    UserProfile fromUserProfileCreateRequest(UserProfileCreateRequest userProfileCreateRequest);
    // search user by name
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
    // get all user
    List<UserProfileResponse> toUserProfileResponseList(List<UserProfile> userProfiles);
    // update user

//    UserProfileResponse fromUserProfileUpdateRequest(UserProfileUpdateRequest userProfileUpdateRequest, @MappingTarget UserProfile userProfile);
    // update profile images
    // response profile images
    ProfileImageResponse toProfileImageResponse(UserProfile userProfile);
}
