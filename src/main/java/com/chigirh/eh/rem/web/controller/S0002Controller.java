package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.dto.Notice;
import com.chigirh.eh.rem.web.facade.UserRoleFacade;
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

    @GetMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Notice notice, Model model) {
        return "home/index";
    }
}
