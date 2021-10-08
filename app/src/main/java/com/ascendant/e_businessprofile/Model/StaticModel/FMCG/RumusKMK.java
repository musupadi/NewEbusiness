package com.ascendant.e_businessprofile.Model.StaticModel.FMCG;

import com.ascendant.e_businessprofile.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RumusKMK {
    @SerializedName("data")
    @Expose
    public DataModel data = new DataModel();

    @SerializedName("range_bawah")
    @Expose
    public String range_bawah;

    @SerializedName("range_atas")
    @Expose
    public String range_atas;
}
