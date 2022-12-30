package tt.hashtranslator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.dto.response.ResultOfApplicationDto;
import tt.hashtranslator.exception.ApplicationNotFoundException;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.ApplicationRepository;
import tt.hashtranslator.repository.HashRepository;
import tt.hashtranslator.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final HashRepository hashRepository;

    @Override
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

    @Override
    public ResultOfApplicationDto getResultOfApplication(String applicationId) {

        final Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        return ResultOfApplicationDto.fromApplication(application);
    }
}
