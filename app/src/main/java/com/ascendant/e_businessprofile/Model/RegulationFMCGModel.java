package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegulationFMCGModel {
    @SerializedName("data")
    @Expose
    public DataRegulationFMCGModel data = new DataRegulationFMCGModel();

    public DataRegulationFMCGModel getData() {
        return data;
    }

    public void setData(DataRegulationFMCGModel data) {
        this.data = data;
    }
}

