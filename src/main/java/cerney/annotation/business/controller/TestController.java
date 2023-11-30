package cerney.annotation.business.controller;

import cerney.annotation.business.contract.dto.UserInfoDTO;
import cerney.annotation.business.contract.response.TestResponse;
import cerney.annotation.custom.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Session
    @GetMapping("/test")
    public ResponseEntity<TestResponse> doTest(UserInfoDTO userInfoDTO) {
        //아무 작업없이 DTO에 테스트 데이터가 들어왔는지 확인.
        System.out.println(userInfoDTO);

        TestResponse testResponse = TestResponse.builder()
            .userId(userInfoDTO.getUserId())
            .userName(userInfoDTO.getUserName())
            .build();

        return ResponseEntity.ok(testResponse);
    }
}
