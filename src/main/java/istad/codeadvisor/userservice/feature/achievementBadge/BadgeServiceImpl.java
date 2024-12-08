package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import istad.codeadvisor.userservice.mapper.BadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        achievementBadge.setIsDeleted(false);
        badgeRepository.save(achievementBadge);
        return badgeMapper.toAchievementBadge(achievementBadge);
    }

    // get badge
    @Override
    public BadgeResponse getBadgeByName(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .filter(badge -> !badge.getIsDeleted().equals(true))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        return badgeMapper.toAchievementBadge(achievementBadge);
    }

    // update badge
    @Override
    public BadgeResponse updateBadgeByName(String badgeName, BadgeUpdateRequest badgeUpdateRequest) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        badgeMapper.fromBadgeUpdateRequest(badgeUpdateRequest, achievementBadge);
        badgeRepository.save(achievementBadge);
        return badgeMapper.toAchievementBadge(achievementBadge);
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

    // delete
    @Override
    public void deleteBadge(String badgeName) {
        AchievementBadge achievementBadge = badgeRepository.findByBadgeName(badgeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "We did not find this badge."));
        badgeRepository.delete(achievementBadge);
        badgeRepository.save(achievementBadge);
    }

    // publish
}
