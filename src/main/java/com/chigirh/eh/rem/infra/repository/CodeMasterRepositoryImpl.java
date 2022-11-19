package com.chigirh.eh.rem.infra.repository;

import com.chigirh.eh.rem.domain.common.CodeMasterType;
import com.chigirh.eh.rem.domain.model.common.CodeMaster;
import com.chigirh.eh.rem.domain.repository.common.CodeMasterRepository;
import com.chigirh.eh.rem.infra.config.DataAccess;
import com.chigirh.eh.rem.infra.mapper.MasterCodeMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CodeMasterRepositoryImpl implements CodeMasterRepository {
    private final MasterCodeMapper masterCodeMapper;

    // sorter by display order
    @Override
    @DataAccess(process = "master_code find.")
    public List<CodeMaster> fetchByCodeType(CodeMasterType codeType) {
        var entities = masterCodeMapper.findByCodeType(codeType.getValue());
        return entities.stream()
            .map(e -> new CodeMaster(e.getCodeValue(), e.getName(), e.getCodeOrder()))
            .collect(Collectors.toList());
    }
}
