package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnakPerusahaan {
        @SerializedName("id_anak_perusahaan")
        @Expose
        public String id_anak_perusahaan;

        @SerializedName("id_perusahaan")
        @Expose
        public String id_perusahaan;

        @SerializedName("nama_anak_perusahaan")
        @Expose
        public String nama_anak_perusahaan;

        @SerializedName("jenis_anak_perusahaan")
        @Expose
        public String jenis_anak_perusahaan;

        @SerializedName("asset_total_perusahaan")
        @Expose
        public String asset_total_perusahaan;

        @SerializedName("persentase_total_perusahaan")
        @Expose
        public String persentase_total_perusahaan;

        @SerializedName("data")
        @Expose
        public String data;

}
