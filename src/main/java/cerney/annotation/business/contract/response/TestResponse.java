package cerney.annotation.business.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TestResponse {
    private String userId;
    private String userName;
}
