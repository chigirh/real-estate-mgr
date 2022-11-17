package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.port.RealEstatcFetchPort;
import com.chigirh.eh.rem.domain.port.RealEstateUpdatePort;
import com.chigirh.eh.rem.web.converter.S0005Converter;
import com.chigirh.eh.rem.web.dto.S0005Form;
import com.chigirh.eh.rem.web.dto.session.Notice;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * S0005(物件詳細画面).
 */
@Controller
@RequiredArgsConstructor
public class S0005Controller {

    private static final Logger log = LoggerFactory.getLogger(S0005Controller.class);

    private final S0004Controller s0004Controller;

    private final RealEstatcFetchPort realEstatcFetchPort;
    private final RealEstateUpdatePort realEstateUpdatePort;

    private final S0005Converter converter;

    private final Notice notice;

    @GetMapping("/real-estate/detail")
    public String index(
        @AuthenticationPrincipal OidcUser user,
        @RequestParam("reId") String reId,
        @ModelAttribute S0005Form s0005Form,
        Model model
    ) {
        var input = new RealEstatcFetchPort.Input(reId);
        var output = realEstatcFetchPort.useCase(input);

        if (output.result() == null) {
            notice.warn("登録されていない物件");
            model.addAttribute("notice", notice);
            return "redirect:/real-estate/list";
        }

        converter.convert(s0005Form, output);

        model.addAttribute("pdfImage", "data:application/pdf;base64," + s0005Form.getPdf());
        model.addAttribute("reId", reId);

        return "real-estate/detail/index";
    }

    @PostMapping("/real-estate/detail")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @RequestParam("reId") String reId,
        @Validated @ModelAttribute S0005Form s0005Form,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            return "real-estate/detail/index";
        }

        var input = converter.convert(reId, s0005Form);
        var output = realEstateUpdatePort.useCase(input);

        if (0 < output.result()) {
            notice.success("更新成功");
        } else {
            notice.warn("更新失敗");
        }

        return "redirect:/real-estate/list";

    }

}
