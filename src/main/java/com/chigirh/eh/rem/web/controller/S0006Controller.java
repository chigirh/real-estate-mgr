package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.error.ValidationError;
import com.chigirh.eh.rem.domain.port.RealEstateBulkCreatePort;
import com.chigirh.eh.rem.web.converter.S0006Converter;
import com.chigirh.eh.rem.web.dto.S0006Form;
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
 * S0006(物件一括登録画面).
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class S0006Controller {

    private final RealEstateBulkCreatePort realEstateBulkCreatePort;

    private final S0006Converter converter;

    private final Notice notice;


    @GetMapping("/real-estate/bulk")
    public String index(@AuthenticationPrincipal OidcUser user, S0006Form s0006Form, Model model) {
        return "real-estate/bulk/index";
    }

    @PostMapping("/real-estate/bulk")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @Validated @ModelAttribute S0006Form s0006Form,
        BindingResult result,
        Model model
    ) {

        if (result.hasErrors()) {
            return "real-estate/bulk/index";
        }

        RealEstateBulkCreatePort.Input input;
        try {
            input = converter.convert(s0006Form);
        } catch (ValidationError e) {
            e.getMessages().forEach(notice::error);
            return "redirect:/real-estate/bulk";
        }

        realEstateBulkCreatePort.useCase(input);
        notice.success(input.models().size() + "件の物件の登録に成功");

        return "redirect:/";
    }
}
