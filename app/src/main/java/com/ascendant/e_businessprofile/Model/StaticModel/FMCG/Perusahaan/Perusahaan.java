package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Perusahaan {
    @SerializedName("detail")
    @Expose
    public DetailPerusahaan detailPerusahaan = new DetailPerusahaan();

    @SerializedName("pemegang_saham")
    @Expose
    List<PemegangSaham> pemegang_saham;

    @SerializedName("anak_perusahaan")
    @Expose
    List<AnakPerusahaan> anak_perusahaan;

    @SerializedName("provinsi")
    @Expose
    public String provinsi;

    @SerializedName("kab_kota")
    @Expose
    public String kab_kota;

    @SerializedName("data")
    @Expose
    List<DataPerusahaan> data;

    public DetailPerusahaan getDetailPerusahaan() {
        return detailPerusahaan;
    }

    public void setDetailPerusahaan(DetailPerusahaan detailPerusahaan) {
        this.detailPerusahaan = detailPerusahaan;
    }

    public List<PemegangSaham> getPemegang_saham() {
        return pemegang_saham;
    }

    public void setPemegang_saham(List<PemegangSaham> pemegang_saham) {
        this.pemegang_saham = pemegang_saham;
    }

    public List<AnakPerusahaan> getAnak_perusahaan() {
        return anak_perusahaan;
    }

    public void setAnak_perusahaan(List<AnakPerusahaan> anak_perusahaan) {
        this.anak_perusahaan = anak_perusahaan;
    }

    public List<DataPerusahaan> getData() {
        return data;
    }

    public void setData(List<DataPerusahaan> data) {
        this.data = data;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }


}
