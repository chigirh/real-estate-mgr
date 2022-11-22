package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.error.SystemError;
import com.chigirh.eh.rem.domain.error.ValidationError;
import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.port.RealEstateBulkCreatePort;
import com.chigirh.eh.rem.web.dto.S0003Form;
import com.chigirh.eh.rem.web.dto.S0006CsvData;
import com.chigirh.eh.rem.web.dto.S0006Form;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Component
@RequiredArgsConstructor
public class S0006Converter {

    private static final String NOTE_FORMAT = "【案内方法】\n%s\n【保証会社】\n%s\n【初期費用】\n%s\n【その他】\n%s\n【URL】\n%s";
    private static final String VALID_FORMAT = "%s件目:%s";

    private final Validator validator;

    private final S0003Converter s0003Converter;

    public RealEstateBulkCreatePort.Input convert(S0006Form s0006Form) {

        var csvMapper = new CsvMapper();
        var csvSchema = csvMapper.schemaFor(S0006CsvData.class).withHeader();

        var models = new ArrayList<RealEstate>();

        try (var csvStream = s0006Form.getUploadFile().getInputStream()) {

            MappingIterator<S0006CsvData> iterator = csvMapper
                .readerFor(S0006CsvData.class)
                .with(csvSchema)
                .readValues(csvStream);

            var row = 0;
            var validationMessages = new ArrayList<String>();

            while (iterator.hasNext()) {
                row++;
                var csvData = iterator.next();
                var s0003Form = toS0003Form(csvData);

                validationMessages.addAll(valid(s0003Form, row));
                if (!validationMessages.isEmpty()) {
                    continue;
                }

                var s0003Input = s0003Converter.convert(s0003Form);
                models.add(s0003Input.model());
            }

            if (!validationMessages.isEmpty()) {
                throw new ValidationError(validationMessages);
            }


        } catch (IOException e) {
            throw new SystemError(e.getMessage(), e);
        }


        return new RealEstateBulkCreatePort.Input(models);
    }

    private S0003Form toS0003Form(S0006CsvData csvData) {
        var form = new S0003Form();
        form.setReName(csvData.getReName());
        form.setReNameKana(csvData.getReNameKana());
        form.setArea1(csvData.getArea1());
        form.setArea2(csvData.getArea2());
        form.setArea3(csvData.getArea3());
        form.setAddress(csvData.getAddress());
        form.setRentPrice(csvData.getRentPrice());
        form.setCondoFee(csvData.getCondoFee());
        form.setWaterFee(csvData.getWaterFee());
        form.setOtherFee(csvData.getOtherFee());
        form.setMgrCompanyName(csvData.getMgrCompanyName());
        form.setMgrCompanyTel(csvData.getMgrCompanyTel());
        form.setForeignerLiveSts(csvData.getForeignerLiveSts());

        var note = String.format(
            NOTE_FORMAT,
            csvData.getNoteGuide(),
            csvData.getNoteGuaranteeCompany(),
            csvData.getNoteIniCost(),
            csvData.getNoteOther(),
            csvData.getUrl()
        );

        form.setNote(note);

        return form;
    }

    public List<String> valid(S0003Form s0003Form, int row) {
        Set<ConstraintViolation<S0003Form>> errorResult = validator.validate(s0003Form);

        return errorResult.stream()
            .map(e -> String.format(VALID_FORMAT, row, e.getMessage()))
            .collect(Collectors.toList());
    }
}
