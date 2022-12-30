package tt.hashtranslator.dto.response;

import lombok.Data;
import tt.hashtranslator.model.Application;
import tt.hashtranslator.model.Hash;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ResultOfApplicationDto {

    private Map<String, String> hashes;

    public static ResultOfApplicationDto fromApplication(Application application) {
        final ResultOfApplicationDto resultOfApplicationDto = new ResultOfApplicationDto();
        final List<Hash> hashes = application.getHashes();

        final Map<String, String> hashesWithDecodeResult = hashes.stream().collect(Collectors.toMap(Hash::getHash, Hash::getDecodeResult));
        resultOfApplicationDto.setHashes(hashesWithDecodeResult);

        return resultOfApplicationDto;
    }
}
