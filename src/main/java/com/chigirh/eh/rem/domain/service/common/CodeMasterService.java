package com.chigirh.eh.rem.domain.service.common;

import com.chigirh.eh.rem.domain.common.CodeMasterType;
import com.chigirh.eh.rem.domain.model.common.CodeMaster;
import com.chigirh.eh.rem.domain.repository.common.CodeMasterRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeMasterService {

    private final CodeMasterRepository codeMasterRepository;

    public List<CodeMaster> fetchForeignerLiveStatus() {
        return codeMasterRepository.fetchByCodeType(CodeMasterType.FOREIGNER_LIVE_STS);
    }
}
