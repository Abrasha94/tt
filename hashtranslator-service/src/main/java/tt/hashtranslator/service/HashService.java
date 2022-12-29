package tt.hashtranslator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tt.hashtranslator.dto.response.ResultOfApplicationDto;
import tt.hashtranslator.exception.ApplicationNotFoundException;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.repository.ApplicationRepository;

@Service
@RequiredArgsConstructor
public class HashService {

    private final ApplicationRepository applicationRepository;

    public ResultOfApplicationDto getResultOfApplication(String applicationId) {

        final Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        return ResultOfApplicationDto.fromApplication(application);
    }
}
