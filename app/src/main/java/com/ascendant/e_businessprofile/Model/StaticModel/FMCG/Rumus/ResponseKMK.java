package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus;

import com.ascendant.e_businessprofile.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseKMK {
    @SerializedName("data")
    @Expose
    public RumusKMK data = new RumusKMK();
}
