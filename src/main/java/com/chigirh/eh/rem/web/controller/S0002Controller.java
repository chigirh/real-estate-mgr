package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.web.dto.session.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * S0002(業務選択画面).
 */
@Controller
@RequiredArgsConstructor
public class S0002Controller {

    private final Notice notice;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        return "home/index";
    }
}
