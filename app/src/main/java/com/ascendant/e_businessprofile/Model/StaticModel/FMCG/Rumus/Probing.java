package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus;

import com.ascendant.e_businessprofile.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Probing {
    @SerializedName("data")
    @Expose
    List<DataModel> data;

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
