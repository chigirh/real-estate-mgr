package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.port.RealEstateSearchPort;
import com.chigirh.eh.rem.domain.service.RealEstateService;
import com.chigirh.eh.rem.web.converter.S0004Converter;
import com.chigirh.eh.rem.web.dto.Notice;
import com.chigirh.eh.rem.web.dto.S0004Form;
import com.chigirh.eh.rem.web.facade.UserRoleFacade;
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

/**
 * S0004(物件一覧画面).
 */
@Controller
@RequiredArgsConstructor
public class S0004Controller {

    private static final Logger log = LoggerFactory.getLogger(S0004Controller.class);

    private final RealEstateService realEstateService;
    private final RealEstateSearchPort realEstateSearchPort;

    private final S0004Converter converter;

    @GetMapping("/real-estate/list")
    public String index(@AuthenticationPrincipal OidcUser user, Notice notice, S0004Form s0004Form, Model model) {
        model.addAttribute("areas", realEstateService.fetchAreas());
        model.addAttribute("defaultArea", AreasConst.DEFAULT);

        return "real-estate/list/index";
    }

    @PostMapping("/real-estate/list")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @Validated @ModelAttribute S0004Form s0004Form,
        BindingResult result,
        Model model
    ) {
        var condition = new RealEstateSearchCondition();
        condition.setReName(s0004Form.getReName());
        condition.setArea(s0004Form.getArea());
        condition.setRentPrice(s0004Form.getRentPrice());
        condition.setForeignerLiveSts(s0004Form.getForeignerLiveSts());

        var input = new RealEstateSearchPort.Input(condition);

        var output = realEstateSearchPort.useCase(input);
        var results = output.results();

        var notice = Notice.builder().info("検索結果" + results.size() + "件").build();
        model.addAttribute("notice", notice);

        var rows = converter.convert(output);

        model.addAttribute("rows", rows);

        return index(user, notice, s0004Form, model);
    }
}
