package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PemegangSaham {


        @SerializedName("id_pemegang_saham")
        @Expose
        public String id_pemegang_saham;

        @SerializedName("id_perusahaan")
        @Expose
        public String id_perusahaan;

        @SerializedName("nama_pemegang_saham")
        @Expose
        public String nama_pemegang_saham;

        @SerializedName("jumlah_saham")
        @Expose
        public String jumlah_saham;

        @SerializedName("persentase")
        @Expose
        public String persentase;



}
