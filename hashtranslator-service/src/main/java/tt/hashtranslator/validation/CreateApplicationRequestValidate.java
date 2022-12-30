package tt.hashtranslator.validation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateApplicationRequestValidate {

    public boolean isValid(List<String> hashes) {

        final long count = hashes.stream().filter(hash -> hash.length() == 32).count();

        return hashes.size() == count;
    }
}
