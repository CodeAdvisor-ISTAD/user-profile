package istad.codeadvisor.userservice.feature.edituserprofile;

import istad.codeadvisor.userservice.domain.EditUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditUserProfileRepository extends MongoRepository<EditUserProfile, String> {
    boolean existsByUsername(String username);
    Optional<EditUserProfile> findByUsername(String username);
    Optional<EditUserProfile> findByFamilyNameAndGivenNameAndUsername(String firstname, String lastname, String username);
}
