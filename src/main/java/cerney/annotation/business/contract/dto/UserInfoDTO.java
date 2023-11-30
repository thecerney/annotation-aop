package cerney.annotation.business.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return "UserInfoDTO{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            '}';
    }
}
