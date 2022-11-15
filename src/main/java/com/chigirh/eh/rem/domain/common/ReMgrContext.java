package com.chigirh.eh.rem.domain.common;

import com.chigirh.eh.rem.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "re-mgr")
public class ReMgrContext {
    private List<User> users = new ArrayList<>();
}
