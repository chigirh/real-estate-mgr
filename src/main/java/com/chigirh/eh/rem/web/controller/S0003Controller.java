package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.converter.S0003Converter;
import com.chigirh.eh.rem.web.dto.Notice;
import com.chigirh.eh.rem.web.dto.S0003Form;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Controller
@RequiredArgsConstructor
public class S0003Controller {

    private static final Logger log = LoggerFactory.getLogger(S0003Controller.class);

    private final RealEstateCreatePort realEstateCreatePort;

    private final S0003Converter converter;

    @GetMapping("/real-estate/register")
    public String index(S0003Form s0003Form, Model model) {


        s0003Form.setReName("物件");
        s0003Form.setReNameKana("ブッケン");
        s0003Form.setArea1("吉塚");
        s0003Form.setAddress("住所");
        s0003Form.setRentPrice(50000);

        return "real-estate/register/index";
    }

    @PostMapping("/real-estate/register")
    public String submit(
        @Validated @ModelAttribute S0003Form s0003Form,
        BindingResult result,
        Model model) {

        if (result.hasErrors()) {
            return index(s0003Form, model);
        }

        var input = converter.convert(s0003Form);
        realEstateCreatePort.useCase(input);

        var notice = Notice.builder().success("物件の登録に成功").build();
        model.addAttribute("notice", notice);

        return "home/index";
    }
}