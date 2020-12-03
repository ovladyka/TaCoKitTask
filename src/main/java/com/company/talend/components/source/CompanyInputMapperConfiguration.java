package com.company.talend.components.source;

import java.io.Serializable;

import com.company.talend.components.dataset.MyDataset;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
    @GridLayout.Row({ "dataset" })
})
@Documentation("TODO fill the documentation for this configuration")
public class CompanyInputMapperConfiguration implements Serializable {
    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private MyDataset dataset;

    public MyDataset getDataset() {
        return dataset;
    }

    public CompanyInputMapperConfiguration setDataset(MyDataset dataset) {
        this.dataset = dataset;
        return this;
    }
}