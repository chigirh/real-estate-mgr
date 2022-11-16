package com.chigirh.eh.rem.web.core.filter;

import com.chigirh.eh.rem.web.facade.UserRoleFacade;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    private final UserRoleFacade userRoleFacade;

    @Before("execution(* com.chigirh.eh.rem.web.controller.*Controller.*(..))")
    public void setRoleIntercept(JoinPoint pjp) {

        var argNames = ((CodeSignature) pjp.getSignature()).getParameterNames();

        var userIdx = -1;
        var modelIdx = -1;

        for (int i = 0; i < argNames.length; i++) {
            if ("user".equals(argNames[i])) {
                userIdx = i;
            }
            if ("model".equals(argNames[i])) {
                modelIdx = i;
            }
        }

        var argValues = pjp.getArgs();

        var user = (OidcUser) argValues[userIdx];
        var model = (Model) argValues[modelIdx];

        userRoleFacade.setRoles(user, model);

        logger.info("user id:{}", user.getEmail());
    }
}
