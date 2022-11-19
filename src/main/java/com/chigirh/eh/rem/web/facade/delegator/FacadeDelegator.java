package com.chigirh.eh.rem.web.facade.delegator;

import com.chigirh.eh.rem.web.facade.ForeignerLiveStatusFacade;
import com.chigirh.eh.rem.web.facade.UserRoleFacade;
import com.chigirh.eh.rem.web.facade.common.FacadeConst;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class FacadeDelegator {

    private final UserRoleFacade userRoleFacade;
    private final ForeignerLiveStatusFacade foreignerLiveStatusFacade;

    @Qualifier("facadeSettingsMap")
    private final Map<String, Set<String>> facadeSettingsMap;

    public void use(Model model, OidcUser user, String uri) {

        // settings user role
        var role = userRoleFacade.setRoles(user, model);

        // settings facades
        var fls1 = facadeSettingsMap.get(FacadeConst.ForeignerLiveStatus.SET);
        if (fls1.contains(uri)) {
            foreignerLiveStatusFacade.set(model);
        }

        var fls2 = facadeSettingsMap.get(FacadeConst.ForeignerLiveStatus.SET_BY_ROLE);
        if (fls2.contains(uri)) {
            foreignerLiveStatusFacade.setByRole(role, model);
        }
    }
}
