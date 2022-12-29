package tt.hashtranslator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tt.hashtranslator.exception.ApplicationNotFoundException;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.ApplicationRepository;
import tt.hashtranslator.repository.HashRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashService {

    private final HashRepository hashRepository;
    private final ApplicationRepository applicationRepository;

    @Async
    public void decodeHash(String applicationId) {
        final Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        final List<Hash> hashes = application.getHashes();
    }
}
