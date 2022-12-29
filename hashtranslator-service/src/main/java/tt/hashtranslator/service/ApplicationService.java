package tt.hashtranslator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.ApplicationRepository;
import tt.hashtranslator.repository.HashRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final HashRepository hashRepository;

    public String createApplication(CreateApplicationDto dto) {

        final List<String> stringHashes = dto.getHashes();

        final List<Hash> hashes = stringHashes.stream().map(hash -> hashRepository.insert(new Hash(hash))).collect(Collectors.toList());

        Application application = new Application();
        application.setHashes(hashes);

        final Application savedApplication = applicationRepository.insert(application);
        return savedApplication.getId();
    }

}
