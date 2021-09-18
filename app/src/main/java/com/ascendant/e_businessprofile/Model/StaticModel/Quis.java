package com.ascendant.e_businessprofile.Model.StaticModel;

public class Quis {
    private String kategori;
    private String skor;
    public Quis(){

    }

    public Quis(String kategori, String skor){
        this.kategori=kategori;
        this.skor=skor;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }
}
