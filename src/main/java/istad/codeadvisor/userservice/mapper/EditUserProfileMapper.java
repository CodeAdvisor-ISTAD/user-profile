package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.EditUserProfile;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileCreateRequest;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileResponse;
import istad.codeadvisor.userservice.feature.edituserprofile.dto.EditUserProfileUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface EditUserProfileMapper {
    // create user
    EditUserProfile fromEditUserProfileCreateRequest(EditUserProfileCreateRequest editUserProfileCreateRequest);
    // search user by name
    EditUserProfileResponse toEditUserProfileResponse(EditUserProfile editUserProfile);
    // get all user
    List<EditUserProfileResponse> toEditUserProfileResponseList(List<EditUserProfile> editUserProfiles);
    // update user
    void fromEditUserProfileUpdateRequest(EditUserProfileUpdateRequest editUserProfileUpdateRequest, @MappingTarget EditUserProfile editUserProfile);
}
