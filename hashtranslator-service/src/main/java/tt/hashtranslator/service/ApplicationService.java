package tt.hashtranslator.service;

import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.dto.response.ResultOfApplicationDto;

public interface ApplicationService {

    String createApplication(CreateApplicationDto dto);

    ResultOfApplicationDto getResultOfApplication(String applicationId);
}
