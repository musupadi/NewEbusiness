package com.ascendant.e_businessprofile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    //Compliance
    @SerializedName("link_video")
    @Expose
    public String link_video;

    @SerializedName("judul_video")
    @Expose
    public String judul_video;

    @SerializedName("thumbnail_video")
    @Expose
    public String thumbnail_video;

    @SerializedName("tgl_upload_video")
    @Expose
    public String tgl_upload_video;

    //Supporting Industries
    @SerializedName("nama_provinsi")
    @Expose
    public String nama_provinsi;

    @SerializedName("link_alkes")
    @Expose
    public String link_alkes;

    //List Of Hospital
    @SerializedName("id_provinsi")
    @Expose
    public String id_provinsi;

    @SerializedName("latitude")
    @Expose
    public String latitude;

    @SerializedName("longitude")
    @Expose
    public String longitude;

    @SerializedName("luas_provinsi")
    @Expose
    public String luas_provinsi;

    @SerializedName("total_penduduk_provinsi")
    @Expose
    public String total_penduduk_provinsi;

    @SerializedName("kepadatan_penduduk_provinsi")
    @Expose
    public String kepadatan_penduduk_provinsi;

    @SerializedName("pdrb_provinsi")
    @Expose
    public String pdrb_provinsi;

    @SerializedName("food_provinsi")
    @Expose
    public String food_provinsi;

    @SerializedName("non_food_provinsi")
    @Expose
    public String non_food_provinsi;

    @SerializedName("non_food_filter_provinsi")
    @Expose
    public String non_food_filter_provinsi;

    @SerializedName("market_size_food")
    @Expose
    public String market_size_food;

    @SerializedName("market_size_non_food")
    @Expose
    public String market_size_non_food;

    @SerializedName("total_market_perbulan")
    @Expose
    public String total_market_perbulan;

    @SerializedName("total_market_pertahun")
    @Expose
    public String total_market_pertahun;

    @SerializedName("id_kab_kota")
    @Expose
    public String id_kab_kota;

    @SerializedName("tipe_kab_kota")
    @Expose
    public String tipe_kab_kota;

    @SerializedName("nama_kab_kota")
    @Expose
    public String nama_kab_kota;

    @SerializedName("kode_pos")
    @Expose
    public String kode_pos;

    @SerializedName("total_penduduk")
    @Expose
    public String total_penduduk;

    @SerializedName("luas_wilayah")
    @Expose
    public String luas_wilayah;

    @SerializedName("kepadatan_penduduk")
    @Expose
    public String kepadatan_penduduk;

    @SerializedName("pdrb_2015")
    @Expose
    public String pdrb_2015;

    @SerializedName("pengeluaran_perkapita_food")
    @Expose
    public String pengeluaran_perkapita_food;

    @SerializedName("pengeluaran_perkapita_non_food")
    @Expose
    public String pengeluaran_perkapita_non_food;

    @SerializedName("tahun_pengeluaran_perkapita")
    @Expose
    public String tahun_pengeluaran_perkapita;

    @SerializedName("kode_rs")
    @Expose
    public String kode_rs;

    @SerializedName("nama_rs")
    @Expose
    public String nama_rs;


    @SerializedName("kelas_rs")
    @Expose
    public String kelas_rs;

    @SerializedName("jumlah_bed_rs")
    @Expose
    public String jumlah_bed_rs;

    @SerializedName("jumlah_tenaga_rs")
    @Expose
    public String jumlah_tenaga_rs;

    @SerializedName("total_rs")
    @Expose
    public String total_rs;

    @SerializedName("total_bed")
    @Expose
    public String total_bed;

    @SerializedName("daftar_rs")
    @Expose
    List<DataModel> daftar_rs;

    //Static
    String Email,Password;
    String idNavigator,Navigator,ONCLICK;
    String id_persi,nama_persi,alamat_persi,telpon_persi,email_persi;


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

    public String getLink_video() {
        return link_video;
    }

    public void setLink_video(String link_video) {
        this.link_video = link_video;
    }

    public String getJudul_video() {
        return judul_video;
    }

    public void setJudul_video(String judul_video) {
        this.judul_video = judul_video;
    }

    public String getThumbnail_video() {
        return thumbnail_video;
    }

    public void setThumbnail_video(String thumbnail_video) {
        this.thumbnail_video = thumbnail_video;
    }

    public String getTgl_upload_video() {
        return tgl_upload_video;
    }

    public void setTgl_upload_video(String tgl_upload_video) {
        this.tgl_upload_video = tgl_upload_video;
    }

    public String getNama_provinsi() {
        return nama_provinsi;
    }

    public void setNama_provinsi(String nama_provinsi) {
        this.nama_provinsi = nama_provinsi;
    }

    public String getLink_alkes() {
        return link_alkes;
    }

    public void setLink_alkes(String link_alkes) {
        this.link_alkes = link_alkes;
    }

    public String getId_provinsi() {
        return id_provinsi;
    }

    public void setId_provinsi(String id_provinsi) {
        this.id_provinsi = id_provinsi;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLuas_provinsi() {
        return luas_provinsi;
    }

    public void setLuas_provinsi(String luas_provinsi) {
        this.luas_provinsi = luas_provinsi;
    }

    public String getTotal_penduduk_provinsi() {
        return total_penduduk_provinsi;
    }

    public void setTotal_penduduk_provinsi(String total_penduduk_provinsi) {
        this.total_penduduk_provinsi = total_penduduk_provinsi;
    }

    public String getKepadatan_penduduk_provinsi() {
        return kepadatan_penduduk_provinsi;
    }

    public void setKepadatan_penduduk_provinsi(String kepadatan_penduduk_provinsi) {
        this.kepadatan_penduduk_provinsi = kepadatan_penduduk_provinsi;
    }

    public String getPdrb_provinsi() {
        return pdrb_provinsi;
    }

    public void setPdrb_provinsi(String pdrb_provinsi) {
        this.pdrb_provinsi = pdrb_provinsi;
    }

    public String getFood_provinsi() {
        return food_provinsi;
    }

    public void setFood_provinsi(String food_provinsi) {
        this.food_provinsi = food_provinsi;
    }

    public String getNon_food_provinsi() {
        return non_food_provinsi;
    }

    public void setNon_food_provinsi(String non_food_provinsi) {
        this.non_food_provinsi = non_food_provinsi;
    }

    public String getNon_food_filter_provinsi() {
        return non_food_filter_provinsi;
    }

    public void setNon_food_filter_provinsi(String non_food_filter_provinsi) {
        this.non_food_filter_provinsi = non_food_filter_provinsi;
    }

    public String getMarket_size_food() {
        return market_size_food;
    }

    public void setMarket_size_food(String market_size_food) {
        this.market_size_food = market_size_food;
    }

    public String getMarket_size_non_food() {
        return market_size_non_food;
    }

    public void setMarket_size_non_food(String market_size_non_food) {
        this.market_size_non_food = market_size_non_food;
    }

    public String getTotal_market_perbulan() {
        return total_market_perbulan;
    }

    public void setTotal_market_perbulan(String total_market_perbulan) {
        this.total_market_perbulan = total_market_perbulan;
    }

    public String getTotal_market_pertahun() {
        return total_market_pertahun;
    }

    public void setTotal_market_pertahun(String total_market_pertahun) {
        this.total_market_pertahun = total_market_pertahun;
    }

    public String getId_kab_kota() {
        return id_kab_kota;
    }

    public void setId_kab_kota(String id_kab_kota) {
        this.id_kab_kota = id_kab_kota;
    }

    public String getTipe_kab_kota() {
        return tipe_kab_kota;
    }

    public void setTipe_kab_kota(String tipe_kab_kota) {
        this.tipe_kab_kota = tipe_kab_kota;
    }

    public String getNama_kab_kota() {
        return nama_kab_kota;
    }

    public void setNama_kab_kota(String nama_kab_kota) {
        this.nama_kab_kota = nama_kab_kota;
    }

    public String getKode_pos() {
        return kode_pos;
    }

    public void setKode_pos(String kode_pos) {
        this.kode_pos = kode_pos;
    }

    public String getTotal_penduduk() {
        return total_penduduk;
    }

    public void setTotal_penduduk(String total_penduduk) {
        this.total_penduduk = total_penduduk;
    }

    public String getLuas_wilayah() {
        return luas_wilayah;
    }

    public void setLuas_wilayah(String luas_wilayah) {
        this.luas_wilayah = luas_wilayah;
    }

    public String getKepadatan_penduduk() {
        return kepadatan_penduduk;
    }

    public void setKepadatan_penduduk(String kepadatan_penduduk) {
        this.kepadatan_penduduk = kepadatan_penduduk;
    }

    public String getPdrb_2015() {
        return pdrb_2015;
    }

    public void setPdrb_2015(String pdrb_2015) {
        this.pdrb_2015 = pdrb_2015;
    }

    public String getPengeluaran_perkapita_food() {
        return pengeluaran_perkapita_food;
    }

    public void setPengeluaran_perkapita_food(String pengeluaran_perkapita_food) {
        this.pengeluaran_perkapita_food = pengeluaran_perkapita_food;
    }

    public String getPengeluaran_perkapita_non_food() {
        return pengeluaran_perkapita_non_food;
    }

    public void setPengeluaran_perkapita_non_food(String pengeluaran_perkapita_non_food) {
        this.pengeluaran_perkapita_non_food = pengeluaran_perkapita_non_food;
    }

    public String getTahun_pengeluaran_perkapita() {
        return tahun_pengeluaran_perkapita;
    }

    public void setTahun_pengeluaran_perkapita(String tahun_pengeluaran_perkapita) {
        this.tahun_pengeluaran_perkapita = tahun_pengeluaran_perkapita;
    }

    public String getKode_rs() {
        return kode_rs;
    }

    public void setKode_rs(String kode_rs) {
        this.kode_rs = kode_rs;
    }

    public String getNama_rs() {
        return nama_rs;
    }

    public void setNama_rs(String nama_rs) {
        this.nama_rs = nama_rs;
    }

    public String getKelas_rs() {
        return kelas_rs;
    }

    public void setKelas_rs(String kelas_rs) {
        this.kelas_rs = kelas_rs;
    }

    public String getJumlah_bed_rs() {
        return jumlah_bed_rs;
    }

    public void setJumlah_bed_rs(String jumlah_bed_rs) {
        this.jumlah_bed_rs = jumlah_bed_rs;
    }

    public String getJumlah_tenaga_rs() {
        return jumlah_tenaga_rs;
    }

    public void setJumlah_tenaga_rs(String jumlah_tenaga_rs) {
        this.jumlah_tenaga_rs = jumlah_tenaga_rs;
    }

    public String getTotal_rs() {
        return total_rs;
    }

    public void setTotal_rs(String total_rs) {
        this.total_rs = total_rs;
    }

    public String getTotal_bed() {
        return total_bed;
    }

    public void setTotal_bed(String total_bed) {
        this.total_bed = total_bed;
    }

    public List<DataModel> getDaftar_rs() {
        return daftar_rs;
    }

    public void setDaftar_rs(List<DataModel> daftar_rs) {
        this.daftar_rs = daftar_rs;
    }

    public String getId_persi() {
        return id_persi;
    }

    public void setId_persi(String id_persi) {
        this.id_persi = id_persi;
    }

    public String getNama_persi() {
        return nama_persi;
    }

    public void setNama_persi(String nama_persi) {
        this.nama_persi = nama_persi;
    }

    public String getAlamat_persi() {
        return alamat_persi;
    }

    public void setAlamat_persi(String alamat_persi) {
        this.alamat_persi = alamat_persi;
    }

    public String getTelpon_persi() {
        return telpon_persi;
    }

    public void setTelpon_persi(String telpon_persi) {
        this.telpon_persi = telpon_persi;
    }

    public String getEmail_persi() {
        return email_persi;
    }

    public void setEmail_persi(String email_persi) {
        this.email_persi = email_persi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
