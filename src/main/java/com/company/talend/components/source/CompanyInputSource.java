package com.company.talend.components.source;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.record.Schema;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;


import com.company.talend.components.service.CompanyComponentService;

@Documentation("TODO fill the documentation for this source")
public class CompanyInputSource implements Serializable {
    private final CompanyInputMapperConfiguration configuration;
    private final CompanyComponentService service;
    private final RecordBuilderFactory builderFactory;
    private CSVReader csvReader;
    private Record.Builder builder;
    private boolean isHeader;
    private String[] headerArr;

    public CompanyInputSource(@Option("configuration") final CompanyInputMapperConfiguration configuration,
                              final CompanyComponentService service,
                              final RecordBuilderFactory builderFactory) {
        this.configuration = configuration;
        this.service = service;
        this.builderFactory = builderFactory;
    }

    @PostConstruct
    public void init() throws IOException, CsvValidationException {
        csvReader = new CSVReader(new FileReader(configuration.getDataset().getFilePath()));

        builder = builderFactory.newRecordBuilder();

        isHeader = configuration.getDataset().getHeader();

        if (isHeader) {
            headerArr = Arrays.stream(csvReader.readNext()).toArray(String[]::new);
        }
        // this method will be executed once for the whole component execution,
        // this is where you can establish a connection for instance
    }

    @Producer
    public Record next() throws IOException, CsvValidationException {

        String[] strArr = csvReader.readNext();

        if (strArr == null) {
            return null;
        }

        if (isHeader && headerArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                builder.withString(headerArr[i], strArr[i]);
            }
        } else {
            for (int i = 0; i < strArr.length; i++) {
                builder.withString("col" + i, strArr[i]);
            }
        }

        return builder.build();

        // this is the method allowing you to go through the dataset associated
        // to the component configuration
        // return null means the dataset has no more data to go through
        // you can use the builderFactory to create a new Record.
    }

    @PreDestroy
    public void release() throws IOException {
        csvReader.close();
        // this is the symmetric method of the init() one,
        // release potential connections you created or data you cached
    }
}