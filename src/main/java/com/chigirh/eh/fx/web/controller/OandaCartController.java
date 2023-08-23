package com.chigirh.eh.fx.web.controller;

import com.chigirh.eh.fx.domain.service.OandaChartQueueService;
import com.chigirh.eh.fx.web.converter.OandaChartConverter;
import com.chigirh.eh.fx.web.model.OandaChartPostForm;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class OandaCartController {

    private final OandaChartQueueService oandaChartQueueService;

    private final OandaChartConverter converter;

    private final Notice notice;

    @GetMapping("/fx/oanda_chart")
    public String index(@AuthenticationPrincipal OidcUser user, OandaChartPostForm oandaChartPostForm, Model model) {
        return "fx/oanda/index";
    }

    @PostMapping("/fx/oanda_chart")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @Validated @ModelAttribute OandaChartPostForm oandaChartPostForm,
        BindingResult result,
        Model model
    ) {

        if (result.hasErrors()) {
            return "fx/oanda/index";
        }

        var oandaCarts = converter.convert(oandaChartPostForm);

        oandaChartQueueService.usecase(oandaCarts);

        notice.success(oandaCarts.size() + "件をキューに追加");

        return "redirect:/";
    }
}
