package tt.hashtranslator.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tt.hashtranslator.client.AuthClient;
import tt.hashtranslator.dto.request.CreateApplicationDto;
import tt.hashtranslator.service.ApplicationService;
import tt.hashtranslator.validation.CreateApplicationRequestValidate;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/hashtranslator")
@RequiredArgsConstructor
public class HashController {

    private final AuthClient authClient;
    private final ApplicationService applicationService;
    private final CreateApplicationRequestValidate validate;

    @PostMapping
    public ResponseEntity<String> createApplication(@Valid @RequestBody CreateApplicationDto requestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String base64) {
        final HttpStatus authorize = authClient.authorize(base64);

        if (authorize.is4xxClientError()) {
            return new ResponseEntity<>("Sorry you are not authorized", HttpStatus.UNAUTHORIZED);
        }

        if (validate.isValid(requestDto.getHashes())) {
            final String applicationId = applicationService.createApplication(requestDto);
            return new ResponseEntity<>(applicationId, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Request is not valid",HttpStatus.BAD_REQUEST);
        }
    }
}
