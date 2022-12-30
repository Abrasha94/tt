package tt.hashtranslator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tt.hashtranslator.model.Hash;

import java.util.Optional;

public interface HashRepository extends MongoRepository<Hash, String> {

    Optional<Hash> findByHash(String hash);
}
