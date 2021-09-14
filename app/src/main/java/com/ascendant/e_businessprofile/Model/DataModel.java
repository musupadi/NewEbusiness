package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {
    //Berita
    @SerializedName("id_berita")
    @Expose
    public String id_berita;

    @SerializedName("id_user")
    @Expose
    public String id_user;

    @SerializedName("kategori_berita")
    @Expose
    public String kategori_berita;

    @SerializedName("judul_berita")
    @Expose
    public String judul_berita;

    @SerializedName("slug_berita")
    @Expose
    public String slug_berita;

    @SerializedName("isi_berita")
    @Expose
    public String isi_berita;

    @SerializedName("cover_berita")
    @Expose
    public String cover_berita;

    @SerializedName("status_berita")
    @Expose
    public String status_berita;

    //Universal
    @SerializedName("created_at")
    @Expose
    public String created_at;

    @SerializedName("updated_at")
    @Expose
    public String updated_at;

    //Divisi
    @SerializedName("id_divisi_mandiri")
    @Expose
    public String id_divisi_mandiri;

    @SerializedName("nama_divisi_mandiri")
    @Expose
    public String nama_divisi_mandiri;

    //Profil
    @SerializedName("email_user")
    @Expose
    public String email_user;

    @SerializedName("nama_user")
    @Expose
    public String nama_user;

    @SerializedName("nip_user")
    @Expose
    public String nip_user;

    @SerializedName("no_telpon_user")
    @Expose
    public String no_telpon_user;

    @SerializedName("divisi")
    @Expose
    public String divisi;

    //POIN
    @SerializedName("total_poin")
    @Expose
    public Integer total_poin;

    //Business Refrence
    @SerializedName("nama_business_refrence")
    @Expose
    public String nama_business_refrence;

    @SerializedName("tgl_upload_business_refrence")
    @Expose
    public String tgl_upload_business_refrence;

    @SerializedName("link_file_business_refrence")
    @Expose
    public String link_file_business_refrence;

    //List Of Probing
    @SerializedName("judul_probing")
    @Expose
    public String judul_probing;

    @SerializedName("isi_probing")
    @Expose
    public String isi_probing;

    @SerializedName("file_probing")
    @Expose
    public String file_probing;

    //Static
    String idNavigator,Navigator,ONCLICK;



    public String getId_berita() {
        return id_berita;
    }

    public void setId_berita(String id_berita) {
        this.id_berita = id_berita;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getKategori_berita() {
        return kategori_berita;
    }

    public void setKategori_berita(String kategori_berita) {
        this.kategori_berita = kategori_berita;
    }

    public String getJudul_berita() {
        return judul_berita;
    }

    public void setJudul_berita(String judul_berita) {
        this.judul_berita = judul_berita;
    }

    public String getSlug_berita() {
        return slug_berita;
    }

    public void setSlug_berita(String slug_berita) {
        this.slug_berita = slug_berita;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public void setIsi_berita(String isi_berita) {
        this.isi_berita = isi_berita;
    }

    public String getCover_berita() {
        return cover_berita;
    }

    public void setCover_berita(String cover_berita) {
        this.cover_berita = cover_berita;
    }

    public String getStatus_berita() {
        return status_berita;
    }

    public void setStatus_berita(String status_berita) {
        this.status_berita = status_berita;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId_divisi_mandiri() {
        return id_divisi_mandiri;
    }

    public void setId_divisi_mandiri(String id_divisi_mandiri) {
        this.id_divisi_mandiri = id_divisi_mandiri;
    }

    public String getNama_divisi_mandiri() {
        return nama_divisi_mandiri;
    }

    public void setNama_divisi_mandiri(String nama_divisi_mandiri) {
        this.nama_divisi_mandiri = nama_divisi_mandiri;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getNip_user() {
        return nip_user;
    }

    public void setNip_user(String nip_user) {
        this.nip_user = nip_user;
    }

    public String getNo_telpon_user() {
        return no_telpon_user;
    }

    public void setNo_telpon_user(String no_telpon_user) {
        this.no_telpon_user = no_telpon_user;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public Integer getTotal_poin() {
        return total_poin;
    }

    public void setTotal_poin(Integer total_poin) {
        this.total_poin = total_poin;
    }

    public String getNavigator() {
        return Navigator;
    }

    public void setNavigator(String navigator) {
        Navigator = navigator;
    }

    public String getIdNavigator() {
        return idNavigator;
    }

    public void setIdNavigator(String idNavigator) {
        this.idNavigator = idNavigator;
    }

    public String getONCLICK() {
        return ONCLICK;
    }

    public void setONCLICK(String ONCLICK) {
        this.ONCLICK = ONCLICK;
    }

    public String getNama_business_refrence() {
        return nama_business_refrence;
    }

    public void setNama_business_refrence(String nama_business_refrence) {
        this.nama_business_refrence = nama_business_refrence;
    }

    public String getTgl_upload_business_refrence() {
        return tgl_upload_business_refrence;
    }

    public void setTgl_upload_business_refrence(String tgl_upload_business_refrence) {
        this.tgl_upload_business_refrence = tgl_upload_business_refrence;
    }

    public String getLink_file_business_refrence() {
        return link_file_business_refrence;
    }

    public void setLink_file_business_refrence(String link_file_business_refrence) {
        this.link_file_business_refrence = link_file_business_refrence;
    }

    public String getJudul_probing() {
        return judul_probing;
    }

    public void setJudul_probing(String judul_probing) {
        this.judul_probing = judul_probing;
    }

    public String getIsi_probing() {
        return isi_probing;
    }

    public void setIsi_probing(String isi_probing) {
        this.isi_probing = isi_probing;
    }

    public String getFile_probing() {
        return file_probing;
    }

    public void setFile_probing(String file_probing) {
        this.file_probing = file_probing;
    }
}
