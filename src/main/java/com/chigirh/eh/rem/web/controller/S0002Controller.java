package com.chigirh.eh.rem.web.controller;

import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.dto.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * S0002(業務選択画面).
 */
@Controller
@RequiredArgsConstructor
public class S0002Controller {

    private final RealEstateCreatePort realEstateCreatePort;

    @GetMapping("/")
    public String index(Notice notice, Model model) {
        return "home/index";
    }
}