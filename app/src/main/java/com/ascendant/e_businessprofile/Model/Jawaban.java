package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jawaban {


    //Quiz
    @SerializedName("jawaban")
    @Expose
    List<DataModel> jawaban;

    @SerializedName("id_quiz")
    @Expose
    public Integer id_quiz;

    @SerializedName("soal_quiz")
    @Expose
    public String soal_quiz;

    @SerializedName("kategori")
    @Expose
    public String kategori;


    public List<DataModel> getJawaban() {
        return jawaban;
    }

    public void setJawaban(List<DataModel> jawaban) {
        this.jawaban = jawaban;
    }

    public Integer getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(Integer id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getSoal_quiz() {
        return soal_quiz;
    }

    public void setSoal_quiz(String soal_quiz) {
        this.soal_quiz = soal_quiz;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
