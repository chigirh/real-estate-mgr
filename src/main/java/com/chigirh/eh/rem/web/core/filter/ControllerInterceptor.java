package com.chigirh.eh.rem.web.core.filter;

import com.chigirh.eh.rem.web.dto.session.Notice;
import com.chigirh.eh.rem.web.facade.common.FacadeConst;
import com.chigirh.eh.rem.web.facade.delegator.FacadeDelegator;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerInterceptor {

    private final FacadeDelegator facadeDelegator;
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

        var methodSignature = (MethodSignature) pjp.getSignature();
        var method = methodSignature.getMethod();

        // common logger.
        String requestUrl = "";
        var getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            requestUrl = String.format(FacadeConst.PATH_FORMAT, "GET", getMapping.value()[0]);
            log.info("{},user id:{}", requestUrl, user.getEmail());
        }

        var postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            requestUrl = String.format(FacadeConst.PATH_FORMAT, "POST", postMapping.value()[0]);
            log.info("{},user id:{}", requestUrl, user.getEmail());
        }

        var deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            requestUrl = String.format(FacadeConst.PATH_FORMAT, "POST", deleteMapping.value()[0]);
            log.info("{},user id:{}", requestUrl, user.getEmail());
        }

        // set notice
        model.addAttribute("notice", notice);

        // delegate facade
        facadeDelegator.use(model, user, requestUrl);

    }
}
