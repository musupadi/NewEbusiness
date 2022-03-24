package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseObject {
    @SerializedName("kode")
    @Expose
    public Integer kode;

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("token_user")
    @Expose
    public String token_user;

    @SerializedName("data")
    @Expose
    DataModel data;

    @SerializedName("benar")
    @Expose
    public Integer benar;

    @SerializedName("new_key")
    @Expose
    public String new_key;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("link")
    @Expose
    public String link;

    @SerializedName("ver")
    @Expose
    public String ver;

    @SerializedName("daftar_rs")
    @Expose
    List<DataModel> daftar_rs;

    @SerializedName("foto_user")
    @Expose
    public String foto_user;

    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken_user() {
        return token_user;
    }

    public void setToken_user(String token_user) {
        this.token_user = token_user;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }


    public List<DataModel> getDaftar_rs() {
        return daftar_rs;
    }

    public void setDaftar_rs(List<DataModel> daftar_rs) {
        this.daftar_rs = daftar_rs;
    }

    public String getNew_key() {
        return new_key;
    }

    public void setNew_key(String new_key) {
        this.new_key = new_key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getFoto_user() {
        return foto_user;
    }

    public void setFoto_user(String foto_user) {
        this.foto_user = foto_user;
    }


    public Integer getBenar() {
        return benar;
    }

    public void setBenar(Integer benar) {
        this.benar = benar;
    }
}
