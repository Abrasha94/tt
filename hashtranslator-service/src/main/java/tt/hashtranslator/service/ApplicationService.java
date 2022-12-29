package tt.hashtranslator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.ApplicationRepository;
import tt.hashtranslator.repository.HashRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final HashRepository hashRepository;

    public String createApplication(CreateApplicationDto dto) {

        final List<String> stringHashes = dto.getHashes();
        List<Hash> hashes = new ArrayList<>();

        for (String stringHash : stringHashes) {

            final Optional<Hash> hash = hashRepository.findByHash(stringHash);

            if (hash.isPresent()) {
                hashes.add(hash.get());
                continue;
            }

            final Hash newHash = hashRepository.insert(new Hash(stringHash));
            hashes.add(newHash);
        }

        Application application = new Application();
        application.setHashes(hashes);

        final Application savedApplication = applicationRepository.insert(application);
        return savedApplication.getId();
    }

}
