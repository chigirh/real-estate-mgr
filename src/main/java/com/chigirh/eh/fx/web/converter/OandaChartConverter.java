package com.chigirh.eh.fx.web.converter;

import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.model.OandaChart;
import com.chigirh.eh.fx.web.model.OandaChartCsvData;
import com.chigirh.eh.fx.web.model.OandaChartPostForm;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OandaChartConverter {

    public List<OandaChart> convert(OandaChartPostForm form) {

        var models = new ArrayList<OandaChart>();
        var csvMapper = new CsvMapper();
        var csvSchema = csvMapper.schemaFor(OandaChartCsvData.class).withHeader();

        // 文字コード
        var charset = Charset.forName("UTF-8");
        try (
            var inputStream = form.getUploadFile().getInputStream();
            var zipInputStream = new ZipInputStream(inputStream, charset);
        ) {

            while ((zipInputStream.getNextEntry()) != null) {

                var out = new ByteArrayOutputStream();
                out.write(zipInputStream.readAllBytes());
                var is = new ByteArrayInputStream(out.toByteArray());

                MappingIterator<OandaChartCsvData> iterator = csvMapper
                    .readerFor(OandaChartCsvData.class)
                    .with(csvSchema)
                    .readValues(is);

                while (iterator.hasNext()) {
                    var csvData = iterator.next();
                    models.add(toModel(csvData));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return models;
    }

    private OandaChart toModel(OandaChartCsvData csvData) {
        var model = new OandaChart();
        model.setDateTime(new ChartLink(LocalDateTime.parse(csvData.getDateTime(), DateTimeFormatter.ISO_DATE_TIME)));
        model.setOpen(csvData.getOpen());
        model.setHigh(csvData.getHigh());
        model.setLow(csvData.getLow());
        model.setClose(csvData.getClose());
        model.setVolume(csvData.getVolume());
        if (Float.isNaN(csvData.getVolumeMa())) {
            model.setVolumeMa(0);
        } else {
            model.setVolumeMa(csvData.getVolumeMa());
        }

        return model;
    }
}
