package com.company.talend.components.dataset;

import java.io.Serializable;

import com.company.talend.components.datastore.CustomDatastore;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.type.DataSet;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@DataSet("MyDataset")
@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
    @GridLayout.Row({ "datastore" }),
    @GridLayout.Row({ "FilePath" }),
    @GridLayout.Row({ "RowSeparator" }),
    @GridLayout.Row({ "FieldSeparator" }),
    @GridLayout.Row({ "SkipEmptyRows" }),
    @GridLayout.Row({ "Header" }),
    @GridLayout.Row({ "Footer" })
})
@Documentation("TODO fill the documentation for this configuration")
public class MyDataset implements Serializable {
    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private CustomDatastore datastore;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String FilePath;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String RowSeparator;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String FieldSeparator;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private boolean SkipEmptyRows;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private boolean Header;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String Footer;

    public CustomDatastore getDatastore() {
        return datastore;
    }

    public MyDataset setDatastore(CustomDatastore datastore) {
        this.datastore = datastore;
        return this;
    }

    public String getFilePath() {
        return FilePath;
    }

    public MyDataset setFilePath(String FilePath) {
        this.FilePath = FilePath;
        return this;
    }

    public String getRowSeparator() {
        return RowSeparator;
    }

    public MyDataset setRowSeparator(String RowSeparator) {
        this.RowSeparator = RowSeparator;
        return this;
    }

    public String getFieldSeparator() {
        return FieldSeparator;
    }

    public MyDataset setFieldSeparator(String FieldSeparator) {
        this.FieldSeparator = FieldSeparator;
        return this;
    }

    public boolean getSkipEmptyRows() {
        return SkipEmptyRows;
    }

    public MyDataset setSkipEmptyRows(boolean SkipEmptyRows) {
        this.SkipEmptyRows = SkipEmptyRows;
        return this;
    }

    public boolean getHeader() {
        return Header;
    }

    public MyDataset setHeader(boolean Header) {
        this.Header = Header;
        return this;
    }

    public String getFooter() {
        return Footer;
    }

    public MyDataset setFooter(String Footer) {
        this.Footer = Footer;
        return this;
    }
}