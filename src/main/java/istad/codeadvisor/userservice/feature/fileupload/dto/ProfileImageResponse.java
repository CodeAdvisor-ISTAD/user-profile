package istad.codeadvisor.userservice.feature.fileupload.dto;

import lombok.Builder;

@Builder
public record ProfileImageResponse (
        String imageUrl, String username) {
}
