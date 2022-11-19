package com.chigirh.eh.rem.domain.repository.common;

import com.chigirh.eh.rem.domain.common.CodeMasterType;
import com.chigirh.eh.rem.domain.model.common.CodeMaster;
import java.util.List;

public interface CodeMasterRepository {
    List<CodeMaster> fetchByCodeType(CodeMasterType codeType);
}
