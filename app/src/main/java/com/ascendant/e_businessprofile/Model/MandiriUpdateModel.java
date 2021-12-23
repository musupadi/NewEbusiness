package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MandiriUpdateModel {

    @SerializedName("files")
    @Expose
    List<DataModel> files;

    @SerializedName("detail")
    @Expose
    DataModel detail;


    public List<DataModel> getFiles() {
        return files;
    }

    public void setFiles(List<DataModel> files) {
        this.files = files;
    }

    public DataModel getDetail() {
        return detail;
    }

    public void setDetail(DataModel detail) {
        this.detail = detail;
    }
}
