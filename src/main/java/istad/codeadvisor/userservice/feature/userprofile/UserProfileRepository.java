package istad.codeadvisor.userservice.feature.userprofile;

import istad.codeadvisor.userservice.domain.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    boolean existsByUsername(String username);
    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Optional<UserProfile> findByAuthorUuid(String authorUuid);

//    Optional<EditUserProfile> findByFamilyNameAndGivenNameAndUsername(String firstname, String lastname, String username);
}
