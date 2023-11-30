package cerney.annotation.aop;

import cerney.annotation.business.contract.dto.UserInfoDTO;
import cerney.annotation.custom.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SessionCheckAdvisor {

    @Pointcut("@annotation(cerney.annotation.custom.Session) && within(cerney.annotation..*Controller)")
    private void sessionAnnotationPointCut() {
    }

    @Around("sessionAnnotationPointCut()")
    public Object checkSession(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        //HttpServletRequest를 RequestContextHolder를 사용하여 접근
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Session session = signature.getMethod().getAnnotation(Session.class);

            if (session != null && session.required()) {
                //request에서 받아온 정보 활용 가능
                String userId = request.getParameter("userId");
                String password = request.getParameter("password");

                //세션 처리 로직 추가 필요.
                //이 예제에서는 로직 대신 임시 값으로 대체.
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                userInfoDTO.setUserId("user1");
                userInfoDTO.setUserName("Cerney");

                //대상 메서드에 선언된 UserInfoDTO 파라미터에 결과값 전달
                for (int j = 0; j < args.length; j++) {
                    if (args[j] instanceof UserInfoDTO) {
                        args[j] = userInfoDTO;
                        break;
                    }
                }
            }
        }

        // 메서드 실행
        return joinPoint.proceed(args);
    }
}
