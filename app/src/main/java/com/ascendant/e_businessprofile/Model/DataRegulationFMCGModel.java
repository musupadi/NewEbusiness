package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataRegulationFMCGModel {
    @SerializedName("Umum")
    @Expose
    List<DataModel> umum;

    @SerializedName("FnB")
    @Expose
    List<DataModel> food;

    @SerializedName("non FnB (Household)")
    @Expose
    List<DataModel> nonFnBHousehold;

    @SerializedName("non FnB (Rokok)")
    @Expose
    List<DataModel> nonFnBRokok;

    public List<DataModel> getUmum() {
        return umum;
    }

    public void setUmum(List<DataModel> umum) {
        this.umum = umum;
    }

    public List<DataModel> getFood() {
        return food;
    }

    public void setFood(List<DataModel> food) {
        this.food = food;
    }

    public List<DataModel> getNonFnBHousehold() {
        return nonFnBHousehold;
    }

    public void setNonFnBHousehold(List<DataModel> nonFnBHousehold) {
        this.nonFnBHousehold = nonFnBHousehold;
    }

    public List<DataModel> getNonFnBRokok() {
        return nonFnBRokok;
    }

    public void setNonFnBRokok(List<DataModel> nonFnBRokok) {
        this.nonFnBRokok = nonFnBRokok;
    }
}

