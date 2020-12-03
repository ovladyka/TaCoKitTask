package com.company.talend.components.output;

import java.io.Serializable;

import com.company.talend.components.dataset.MyDataset;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
    @GridLayout.Row({ "dataset" }),
    @GridLayout.Row({ "Append" })
})
@Documentation("TODO fill the documentation for this configuration")
public class CompanyInputOutputConfiguration implements Serializable {
    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private MyDataset dataset;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private boolean Append;

    public boolean getAppend() {
        return Append;
    }

    public CompanyInputOutputConfiguration setAppend(boolean Append) {
        this.Append = Append;
        return this;
    }

    public MyDataset getDataset() {
        return dataset;
    }

    public CompanyInputOutputConfiguration setDataset(MyDataset dataset) {
        this.dataset = dataset;
        return this;
    }
}