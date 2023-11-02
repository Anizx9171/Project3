package project.model;

import project.config.Config;

import java.io.Serializable;

public class Catalog implements Serializable {
    private Long catalogId;
    private String catalogName;
    private String description;
    private boolean status;

    public Catalog( String catalogName, String description, boolean status) {
        this.catalogId = Config.RandomId();
        this.catalogName = catalogName;
        this.description = description;
        this.status = status;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "catalogId: " + this.catalogId +
                ",\ncatalogName: " + this.catalogName +
                ",\ndescription: " + this.description +
                ",\nstatus: " + this.status + ".\n";
    }
}
