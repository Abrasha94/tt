package tt.hashtranslator.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tt.hashtranslator.client.Md5DecryptClient;
import tt.hashtranslator.exception.HashNotFoundException;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.HashRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Md5DecryptClientImpl implements Md5DecryptClient {

    private static final String EMAIL = "topdisplay4ik@gmail.com";
    private static final String CODE = "527dff0f187b11eb";
    public static final String MD5_URL_WITH_PARAMS =
            "https://md5decrypt.net/en/Api/api.php?hash=%s&hash_type=md5&email=%s&code=%s";

    private final RestTemplate md5DecryptRestTemplate;
    private final HashRepository hashRepository;

    @Override
    @Async("threadPoolTaskExecutor")
    public void decrypt(List<String> hashes) {

        for (String stringHash : hashes) {

            final Hash hash = hashRepository.findByHash(stringHash)
                    .orElseThrow(() -> new HashNotFoundException("Hash not found"));

            if (hash.getDecodeResult() == null) {
                final String decodedHash = md5DecryptRestTemplate
                        .getForObject(String.format(MD5_URL_WITH_PARAMS, stringHash, EMAIL, CODE), String.class);

                hash.setDecodeResult(decodedHash);
                hashRepository.save(hash);
            }
        }
    }
}
