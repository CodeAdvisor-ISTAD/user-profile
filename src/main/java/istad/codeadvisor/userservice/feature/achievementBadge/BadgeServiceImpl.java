package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import istad.codeadvisor.userservice.mapper.BadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService{
    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;

    // create badge
    @Override
    public BadgeResponse createBadge(BadgeCreateRequest badgeCreateRequest) {
        // validate badge's name
        if(badgeRepository.existsByBadgeName(badgeCreateRequest.badgeName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This badge is already exist!!");
        }
        AchievementBadge achievementBadge = badgeMapper.fromBadgeCreateRequest(badgeCreateRequest);
        achievementBadge.setAssignAt(LocalDateTime.now());
        achievementBadge.setIsDeleted(false);
        achievementBadge.setIsPublish(true);
        badgeRepository.save(achievementBadge);
        return badgeMapper.toAchievementBadgeResponse(achievementBadge);
    }

    // get all badges
    @Override
    public List<AchievementBadge> getAllBadges() {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        List<AchievementBadge> achievementBadges = badgeRepository.findAll(sortById);
        return badgeMapper.toAchievementBadgeResponseList(achievementBadges);
    }

    // get badge
    @Override
    public BadgeResponse getBadgeByName(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .filter(badge -> !badge.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        return badgeMapper.toAchievementBadgeResponse(achievementBadge);
    }

    // get badge by user id
    @Override
    public BadgeResponse getBadgeByUserId(String userId) {
        AchievementBadge achievementBadge = badgeRepository.findByUserId(userId)
                .filter(badge -> !badge.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        return badgeMapper.toAchievementBadgeResponse(achievementBadge);
    }



    // update badge
    @Override
    public BadgeResponse updateBadgeByName(String badgeName, BadgeUpdateRequest badgeUpdateRequest) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        badgeMapper.fromBadgeUpdateRequest(badgeUpdateRequest, achievementBadge);
        badgeRepository.save(achievementBadge);
        return badgeMapper.toAchievementBadgeResponse(achievementBadge);
    }

    // is published
    @Override
    public void isPublicBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        achievementBadge.setIsDeleted(false);
        badgeRepository.save(achievementBadge);
    }

    // is unpublished
    @Override
    public void isUnpublishedBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        achievementBadge.setIsDeleted(true);
        badgeRepository.save(achievementBadge);
    }

    // disable badge
    @Override
    public void disableBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        achievementBadge.setIsDeleted(true);
        badgeRepository.save(achievementBadge);
    }

    // enable badge
    @Override
    public void enableBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        achievementBadge.setIsDeleted(false);
        badgeRepository.save(achievementBadge);
    }

    // delete
    @Override
    public void deleteBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        badgeRepository.delete(achievementBadge);
        badgeRepository.save(achievementBadge);
    }
}
