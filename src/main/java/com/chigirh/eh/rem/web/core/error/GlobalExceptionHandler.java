package com.chigirh.eh.rem.web.core.error;

import com.chigirh.eh.rem.web.dto.session.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private final Notice notice;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.error("internal server error.", ex);
        notice.error("Internal Server Error, error message:" + ex.getMessage());

        var mv = new ModelAndView();
        mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mv.setViewName("/");
        return mv;
    }
}
