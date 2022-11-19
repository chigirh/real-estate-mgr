package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.port.RealEstateSearchPort;
import com.chigirh.eh.rem.domain.service.RealEstateService;
import com.chigirh.eh.rem.web.converter.S0004Converter;
import com.chigirh.eh.rem.web.dto.S0004Form;
import com.chigirh.eh.rem.web.dto.S0004TableRow;
import com.chigirh.eh.rem.web.dto.session.Notice;
import com.chigirh.eh.rem.web.dto.session.S0004Condition;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private final Notice notice;

    private final S0004Condition s0004Condition;

    @GetMapping("/real-estate/list")
    public String index(
        @AuthenticationPrincipal OidcUser user,
        @ModelAttribute S0004Form s0004Form,
        @PageableDefault Pageable pageable,
        Model model
    ) {
        // conditions init.
        model.addAttribute("areas", realEstateService.fetchAreas());
        model.addAttribute("defaultArea", AreasConst.DEFAULT);

        // condition restore.
        s0004Form.setReName(s0004Condition.getReName());
        s0004Form.setArea(s0004Condition.getArea());
        s0004Form.setRentPrice(s0004Condition.getRentPrice());
        s0004Form.setForeignerLiveSts(s0004Condition.getForeignerLiveSts());

        // condition cache
        s0004Condition.setPageNumber(pageable.getPageNumber());

        // search
        var page = search(s0004Form, model, pageable);
        notice.info("検索結果" + page.getTotalElements() + "件");

        return "real-estate/list/index";
    }

    @PostMapping("/real-estate/list")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        @Validated @ModelAttribute S0004Form s0004Form,
        @PageableDefault Pageable pageable,
        BindingResult result,
        Model model
    ) {
        // conditions init.
        model.addAttribute("areas", realEstateService.fetchAreas());
        model.addAttribute("defaultArea", AreasConst.DEFAULT);

        // search
        var page = search(s0004Form, model, pageable);
        notice.info("検索結果" + page.getTotalElements() + "件");

        // condition cache
        s0004Condition.setReName(s0004Form.getReName());
        s0004Condition.setArea(s0004Form.getArea());
        s0004Condition.setRentPrice(s0004Form.getRentPrice());
        s0004Condition.setForeignerLiveSts(s0004Form.getForeignerLiveSts());
        s0004Condition.setPageNumber(pageable.getPageNumber());

        return "real-estate/list/index";
    }

    private Page<S0004TableRow> search(S0004Form s0004Form, Model model, Pageable pageable) {
        var condition = new RealEstateSearchCondition();
        condition.setReName(s0004Form.getReName());
        condition.setArea(s0004Form.getArea());
        condition.setRentPrice(s0004Form.getRentPrice());
        condition.setForeignerLiveSts(s0004Form.getForeignerLiveSts());
        condition.setOffset((int) pageable.getOffset());
        condition.setSize(pageable.getPageSize());

        var input = new RealEstateSearchPort.Input(condition);

        var output = realEstateSearchPort.useCase(input);

        var rows = converter.convert(output);
        var page = new PageImpl(rows, pageable, output.result().getTotal());
        model.addAttribute("rows", rows);
        model.addAttribute("page", page);
        return page;
    }
}
