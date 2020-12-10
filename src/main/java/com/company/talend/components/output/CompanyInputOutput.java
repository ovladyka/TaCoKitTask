package com.company.talend.components.output;

import static org.talend.sdk.component.api.component.Icon.IconType.CUSTOM;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.opencsv.CSVWriter;
import org.talend.sdk.component.api.component.Icon;
import org.talend.sdk.component.api.component.Version;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.processor.AfterGroup;
import org.talend.sdk.component.api.processor.BeforeGroup;
import org.talend.sdk.component.api.processor.ElementListener;
import org.talend.sdk.component.api.processor.Input;
import org.talend.sdk.component.api.processor.Processor;
import org.talend.sdk.component.api.record.Record;

import com.company.talend.components.service.CompanyComponentService;
import org.talend.sdk.component.api.record.Schema;

@Version(1)
// default version is 1, if some configuration changes happen between 2 versions you can add a migrationHandler
@Icon(value = CUSTOM, custom = "CompanyOutput") // icon is located at src/main/resources/icons/CompanyOutput.svg
@Processor(name = "CompanyOutput")
@Documentation("TODO fill the documentation for this processor")
public class CompanyInputOutput implements Serializable {
    private final CompanyInputOutputConfiguration configuration;
    private final CompanyComponentService service;
    private CSVWriter csvWriter;
    private boolean isHeader;

    public CompanyInputOutput(@Option("configuration") final CompanyInputOutputConfiguration configuration,
                              final CompanyComponentService service) {
        this.configuration = configuration;
        this.service = service;
    }

    @PostConstruct
    public void init() throws IOException {

        if (configuration.getAppend()) {
            csvWriter = new CSVWriter(new FileWriter(configuration.getDataset().getFilePath(),true));
        } else {
            csvWriter = new CSVWriter(new FileWriter(configuration.getDataset().getFilePath()));
        }
        // this method will be executed once for the whole component execution,
        // this is where you can establish a connection for instance
        // Note: if you don't need it you can delete it
    }

    @BeforeGroup
    public void beforeGroup() {
        // if the environment supports chunking this method is called at the beginning if a chunk
        // it can be used to start a local transaction specific to the backend you use
        // Note: if you don't need it you can delete it
    }

    @ElementListener
    public void onNext(
            @Input final Record defaultInput) {

        final Schema schema = defaultInput.getSchema();

        List<String> objectList = new ArrayList<>();

        schema.getEntries()
                .forEach(entry -> objectList.add(defaultInput.getString(entry.getName())));

        String[] arr = new String[objectList.size()];
        objectList.toArray(arr);

        if (!configuration.getDataset().getHeader() && !isHeader) {
            isHeader = true;
            arr = null;
        }

        csvWriter.writeNext(arr);

        // this is the method allowing you to handle the input(s) and emit the output(s)
        // after some custom logic you put here, to send a value to next element you can use an
        // output parameter and call emit(value).
    }

    @AfterGroup
    public void afterGroup() {
        // symmetric method of the beforeGroup() executed after the chunk processing
        // Note: if you don't need it you can delete it
    }

    @PreDestroy
    public void release() throws IOException {
        csvWriter.close();
        // this is the symmetric method of the init() one,
        // release potential connections you created or data you cached
        // Note: if you don't need it you can delete it
    }
}