package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.converter.S0003Converter;
import com.chigirh.eh.rem.web.dto.S0003Form;
import com.chigirh.eh.rem.web.dto.session.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * S0003(物件登録画面).
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class S0003Controller {

    private final RealEstateCreatePort realEstateCreatePort;

    private final S0003Converter converter;

    private final Notice notice;

    @GetMapping("/real-estate/register")
    public String index(@AuthenticationPrincipal OidcUser user, S0003Form s0003Form, Model model) {
        return "real-estate/register/index";
    }

    @PostMapping("/real-estate/register")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @Validated @ModelAttribute S0003Form s0003Form,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            return index(user, s0003Form, model);
        }

        var input = converter.convert(s0003Form);
        realEstateCreatePort.useCase(input);

        notice.success("物件の登録に成功");

        log.info("register:{}", s0003Form);

        return "redirect:/";
    }
}
