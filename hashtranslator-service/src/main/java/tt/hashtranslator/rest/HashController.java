package tt.hashtranslator.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tt.hashtranslator.client.Md5DecryptClient;
import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.dto.response.ResultOfApplicationDto;
import tt.hashtranslator.exception.ApplicationNotFoundException;
import tt.hashtranslator.service.ApplicationService;
import tt.hashtranslator.validation.CreateApplicationRequestValidate;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/hashtranslator/")
@RequiredArgsConstructor
public class HashController {

    private final Md5DecryptClient md5DecryptClient;
    private final ApplicationService applicationService;
    private final CreateApplicationRequestValidate validate;

    @PostMapping
    public ResponseEntity<String> createApplication(@Valid @RequestBody CreateApplicationDto requestDto) {

        if (validate.isValid(requestDto.getHashes())) {
            final String applicationId = applicationService.createApplication(requestDto);
            md5DecryptClient.decrypt(requestDto.getHashes());
            return new ResponseEntity<>(applicationId, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Request is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultOfApplicationDto> getResultOfApplication(@PathVariable("id") String applicationId) {

        try {
            final ResultOfApplicationDto resultOfApplication = applicationService.getResultOfApplication(applicationId);
            return new ResponseEntity<>(resultOfApplication, HttpStatus.OK);
        } catch (ApplicationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Application not found", e);
        }
    }
}
