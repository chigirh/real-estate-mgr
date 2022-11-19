package com.chigirh.eh.rem.web.core.filter;

import com.chigirh.eh.rem.web.dto.session.Notice;
import com.chigirh.eh.rem.web.facade.ForeignerLiveStatusFacade;
import com.chigirh.eh.rem.web.facade.UserRoleFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerInterceptor {

    private final UserRoleFacade userRoleFacade;
    private final ForeignerLiveStatusFacade foreignerLiveStatusFacade;

    private final Notice notice;

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

        // settings user role
        userRoleFacade.setRoles(user, model);
        model.addAttribute("notice", notice);

        var methodSignature = (MethodSignature) pjp.getSignature();
        var method = methodSignature.getMethod();

        // call facades
        foreignerLiveStatusFacade.set(model);

        // common logger.
        var getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            log.info("path:{},user id:{}", getMapping.value(), user.getEmail());
        }

        var postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            log.info("path:{},user id:{}", postMapping.value(), user.getEmail());
        }
    }
}
