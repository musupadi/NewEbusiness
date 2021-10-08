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

    //Key Of Success
    @SerializedName("jenis_kos")
    @Expose
    public String jenis_kos;

    @SerializedName("kategori_kos")
    @Expose
    public String kategori_kos;

    @SerializedName("judul_kos")
    @Expose
    public String judul_kos;

    @SerializedName("isi_kos")
    @Expose
    public String isi_kos;

    @SerializedName("file_kos")
    @Expose
    public String file_kos;


    //Old EBusiness
    String id_fmcg_param,nama_param,rumus_param,kategori_param,rata_rata_param,penjelasan_param,penjelasan2_param;
    String id_asosiasi,nilaiperkapita;
    String password,nama,no_telpon,status,level_user;
    String pengurus,anggota;
    //Static
    String tgl_registrasi,jenis,
            kelas,direktur,latar_belakang,pemilik,
            alamat,kab_kota,
            telepon,fax,telepon_humas,website,luas_tanah,
            luas_bangunan,tanggal_surat_ijin,
            berlaku_sampai,vvip,vip,kelas_1,kelas_2,kelas_3,icu,picu,nicu,
            tt_bayi,hcu,iccu,tt_operasi,tt_isolasi,dr_og,dr_pd,dr_b,
            dr_rad,dr_rm,dr_an,dr_jp,dr_m,dr_tht,dr_pk,dr_paru_paru,dr_bedah_thoraks,
            dr_bedah_anak,dr_bedah_orthopedi,dr_a,dr_okupasi,dr_urologi,dr_kulit,
            dr_forensik,dr_psikiatri,dr_ofthalmologi,dr_anatomi,dr_jiwa,dr_saraf,dr_lainnya,
            dr_bedah_saraf,dr_bedah_plastik,dr_bedah_mulut,dr_konservasi,
            dr_penyakit_mulut,dr_radiologi,dr_karang_gigi,dr_gigi_anak,dr_gigi_tiruan,dr_periodonsia,
            dr_gigi_lainnya,bidan_pendidikan,radiografi,refraksionis,
            rekam_medis,transfusi_darah,kardiovaskular,epidemiology,prilaku,biostatik,dietisien,admin_kesehatan,bank;


    String Email,Password;
    String idNavigator,Navigator,ONCLICK;
    String id_persi,nama_persi,alamat_persi,telpon_persi,email_persi;
    String no_quis,category_quis,soal_quis,simulasi,jawaban;

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

    public String getJenis_kos() {
        return jenis_kos;
    }

    public void setJenis_kos(String jenis_kos) {
        this.jenis_kos = jenis_kos;
    }

    public String getKategori_kos() {
        return kategori_kos;
    }

    public void setKategori_kos(String kategori_kos) {
        this.kategori_kos = kategori_kos;
    }

    public String getJudul_kos() {
        return judul_kos;
    }

    public void setJudul_kos(String judul_kos) {
        this.judul_kos = judul_kos;
    }

    public String getIsi_kos() {
        return isi_kos;
    }

    public void setIsi_kos(String isi_kos) {
        this.isi_kos = isi_kos;
    }

    public String getFile_kos() {
        return file_kos;
    }

    public void setFile_kos(String file_kos) {
        this.file_kos = file_kos;
    }

    public String getNo_quis() {
        return no_quis;
    }

    public void setNo_quis(String no_quis) {
        this.no_quis = no_quis;
    }

    public String getCategory_quis() {
        return category_quis;
    }

    public void setCategory_quis(String category_quis) {
        this.category_quis = category_quis;
    }

    public String getSoal_quis() {
        return soal_quis;
    }

    public void setSoal_quis(String soal_quis) {
        this.soal_quis = soal_quis;
    }

    public String getSimulasi() {
        return simulasi;
    }

    public void setSimulasi(String simulasi) {
        this.simulasi = simulasi;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getId_asosiasi() {
        return id_asosiasi;
    }

    public void setId_asosiasi(String id_asosiasi) {
        this.id_asosiasi = id_asosiasi;
    }

    public String getNilaiperkapita() {
        return nilaiperkapita;
    }

    public void setNilaiperkapita(String nilaiperkapita) {
        this.nilaiperkapita = nilaiperkapita;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telpon() {
        return no_telpon;
    }

    public void setNo_telpon(String no_telpon) {
        this.no_telpon = no_telpon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel_user() {
        return level_user;
    }

    public void setLevel_user(String level_user) {
        this.level_user = level_user;
    }

    public String getTgl_registrasi() {
        return tgl_registrasi;
    }

    public void setTgl_registrasi(String tgl_registrasi) {
        this.tgl_registrasi = tgl_registrasi;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getDirektur() {
        return direktur;
    }

    public void setDirektur(String direktur) {
        this.direktur = direktur;
    }

    public String getLatar_belakang() {
        return latar_belakang;
    }

    public void setLatar_belakang(String latar_belakang) {
        this.latar_belakang = latar_belakang;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKab_kota() {
        return kab_kota;
    }

    public void setKab_kota(String kab_kota) {
        this.kab_kota = kab_kota;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelepon_humas() {
        return telepon_humas;
    }

    public void setTelepon_humas(String telepon_humas) {
        this.telepon_humas = telepon_humas;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLuas_tanah() {
        return luas_tanah;
    }

    public void setLuas_tanah(String luas_tanah) {
        this.luas_tanah = luas_tanah;
    }

    public String getLuas_bangunan() {
        return luas_bangunan;
    }

    public void setLuas_bangunan(String luas_bangunan) {
        this.luas_bangunan = luas_bangunan;
    }

    public String getTanggal_surat_ijin() {
        return tanggal_surat_ijin;
    }

    public void setTanggal_surat_ijin(String tanggal_surat_ijin) {
        this.tanggal_surat_ijin = tanggal_surat_ijin;
    }

    public String getBerlaku_sampai() {
        return berlaku_sampai;
    }

    public void setBerlaku_sampai(String berlaku_sampai) {
        this.berlaku_sampai = berlaku_sampai;
    }

    public String getVvip() {
        return vvip;
    }

    public void setVvip(String vvip) {
        this.vvip = vvip;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getKelas_1() {
        return kelas_1;
    }

    public void setKelas_1(String kelas_1) {
        this.kelas_1 = kelas_1;
    }

    public String getKelas_2() {
        return kelas_2;
    }

    public void setKelas_2(String kelas_2) {
        this.kelas_2 = kelas_2;
    }

    public String getKelas_3() {
        return kelas_3;
    }

    public void setKelas_3(String kelas_3) {
        this.kelas_3 = kelas_3;
    }

    public String getIcu() {
        return icu;
    }

    public void setIcu(String icu) {
        this.icu = icu;
    }

    public String getPicu() {
        return picu;
    }

    public void setPicu(String picu) {
        this.picu = picu;
    }

    public String getNicu() {
        return nicu;
    }

    public void setNicu(String nicu) {
        this.nicu = nicu;
    }

    public String getTt_bayi() {
        return tt_bayi;
    }

    public void setTt_bayi(String tt_bayi) {
        this.tt_bayi = tt_bayi;
    }

    public String getHcu() {
        return hcu;
    }

    public void setHcu(String hcu) {
        this.hcu = hcu;
    }

    public String getIccu() {
        return iccu;
    }

    public void setIccu(String iccu) {
        this.iccu = iccu;
    }

    public String getTt_operasi() {
        return tt_operasi;
    }

    public void setTt_operasi(String tt_operasi) {
        this.tt_operasi = tt_operasi;
    }

    public String getTt_isolasi() {
        return tt_isolasi;
    }

    public void setTt_isolasi(String tt_isolasi) {
        this.tt_isolasi = tt_isolasi;
    }

    public String getDr_og() {
        return dr_og;
    }

    public void setDr_og(String dr_og) {
        this.dr_og = dr_og;
    }

    public String getDr_pd() {
        return dr_pd;
    }

    public void setDr_pd(String dr_pd) {
        this.dr_pd = dr_pd;
    }

    public String getDr_b() {
        return dr_b;
    }

    public void setDr_b(String dr_b) {
        this.dr_b = dr_b;
    }

    public String getDr_rad() {
        return dr_rad;
    }

    public void setDr_rad(String dr_rad) {
        this.dr_rad = dr_rad;
    }

    public String getDr_rm() {
        return dr_rm;
    }

    public void setDr_rm(String dr_rm) {
        this.dr_rm = dr_rm;
    }

    public String getDr_an() {
        return dr_an;
    }

    public void setDr_an(String dr_an) {
        this.dr_an = dr_an;
    }

    public String getDr_jp() {
        return dr_jp;
    }

    public void setDr_jp(String dr_jp) {
        this.dr_jp = dr_jp;
    }

    public String getDr_m() {
        return dr_m;
    }

    public void setDr_m(String dr_m) {
        this.dr_m = dr_m;
    }

    public String getDr_tht() {
        return dr_tht;
    }

    public void setDr_tht(String dr_tht) {
        this.dr_tht = dr_tht;
    }

    public String getDr_pk() {
        return dr_pk;
    }

    public void setDr_pk(String dr_pk) {
        this.dr_pk = dr_pk;
    }

    public String getDr_paru_paru() {
        return dr_paru_paru;
    }

    public void setDr_paru_paru(String dr_paru_paru) {
        this.dr_paru_paru = dr_paru_paru;
    }

    public String getDr_bedah_thoraks() {
        return dr_bedah_thoraks;
    }

    public void setDr_bedah_thoraks(String dr_bedah_thoraks) {
        this.dr_bedah_thoraks = dr_bedah_thoraks;
    }

    public String getDr_bedah_anak() {
        return dr_bedah_anak;
    }

    public void setDr_bedah_anak(String dr_bedah_anak) {
        this.dr_bedah_anak = dr_bedah_anak;
    }

    public String getDr_bedah_orthopedi() {
        return dr_bedah_orthopedi;
    }

    public void setDr_bedah_orthopedi(String dr_bedah_orthopedi) {
        this.dr_bedah_orthopedi = dr_bedah_orthopedi;
    }

    public String getDr_a() {
        return dr_a;
    }

    public void setDr_a(String dr_a) {
        this.dr_a = dr_a;
    }

    public String getDr_okupasi() {
        return dr_okupasi;
    }

    public void setDr_okupasi(String dr_okupasi) {
        this.dr_okupasi = dr_okupasi;
    }

    public String getDr_urologi() {
        return dr_urologi;
    }

    public void setDr_urologi(String dr_urologi) {
        this.dr_urologi = dr_urologi;
    }

    public String getDr_kulit() {
        return dr_kulit;
    }

    public void setDr_kulit(String dr_kulit) {
        this.dr_kulit = dr_kulit;
    }

    public String getDr_forensik() {
        return dr_forensik;
    }

    public void setDr_forensik(String dr_forensik) {
        this.dr_forensik = dr_forensik;
    }

    public String getDr_psikiatri() {
        return dr_psikiatri;
    }

    public void setDr_psikiatri(String dr_psikiatri) {
        this.dr_psikiatri = dr_psikiatri;
    }

    public String getDr_ofthalmologi() {
        return dr_ofthalmologi;
    }

    public void setDr_ofthalmologi(String dr_ofthalmologi) {
        this.dr_ofthalmologi = dr_ofthalmologi;
    }

    public String getDr_anatomi() {
        return dr_anatomi;
    }

    public void setDr_anatomi(String dr_anatomi) {
        this.dr_anatomi = dr_anatomi;
    }

    public String getDr_jiwa() {
        return dr_jiwa;
    }

    public void setDr_jiwa(String dr_jiwa) {
        this.dr_jiwa = dr_jiwa;
    }

    public String getDr_saraf() {
        return dr_saraf;
    }

    public void setDr_saraf(String dr_saraf) {
        this.dr_saraf = dr_saraf;
    }

    public String getDr_lainnya() {
        return dr_lainnya;
    }

    public void setDr_lainnya(String dr_lainnya) {
        this.dr_lainnya = dr_lainnya;
    }

    public String getDr_bedah_saraf() {
        return dr_bedah_saraf;
    }

    public void setDr_bedah_saraf(String dr_bedah_saraf) {
        this.dr_bedah_saraf = dr_bedah_saraf;
    }

    public String getDr_bedah_plastik() {
        return dr_bedah_plastik;
    }

    public void setDr_bedah_plastik(String dr_bedah_plastik) {
        this.dr_bedah_plastik = dr_bedah_plastik;
    }

    public String getDr_bedah_mulut() {
        return dr_bedah_mulut;
    }

    public void setDr_bedah_mulut(String dr_bedah_mulut) {
        this.dr_bedah_mulut = dr_bedah_mulut;
    }

    public String getDr_konservasi() {
        return dr_konservasi;
    }

    public void setDr_konservasi(String dr_konservasi) {
        this.dr_konservasi = dr_konservasi;
    }

    public String getDr_penyakit_mulut() {
        return dr_penyakit_mulut;
    }

    public void setDr_penyakit_mulut(String dr_penyakit_mulut) {
        this.dr_penyakit_mulut = dr_penyakit_mulut;
    }

    public String getDr_radiologi() {
        return dr_radiologi;
    }

    public void setDr_radiologi(String dr_radiologi) {
        this.dr_radiologi = dr_radiologi;
    }

    public String getDr_karang_gigi() {
        return dr_karang_gigi;
    }

    public void setDr_karang_gigi(String dr_karang_gigi) {
        this.dr_karang_gigi = dr_karang_gigi;
    }

    public String getDr_gigi_anak() {
        return dr_gigi_anak;
    }

    public void setDr_gigi_anak(String dr_gigi_anak) {
        this.dr_gigi_anak = dr_gigi_anak;
    }

    public String getDr_gigi_tiruan() {
        return dr_gigi_tiruan;
    }

    public void setDr_gigi_tiruan(String dr_gigi_tiruan) {
        this.dr_gigi_tiruan = dr_gigi_tiruan;
    }

    public String getDr_periodonsia() {
        return dr_periodonsia;
    }

    public void setDr_periodonsia(String dr_periodonsia) {
        this.dr_periodonsia = dr_periodonsia;
    }

    public String getDr_gigi_lainnya() {
        return dr_gigi_lainnya;
    }

    public void setDr_gigi_lainnya(String dr_gigi_lainnya) {
        this.dr_gigi_lainnya = dr_gigi_lainnya;
    }

    public String getBidan_pendidikan() {
        return bidan_pendidikan;
    }

    public void setBidan_pendidikan(String bidan_pendidikan) {
        this.bidan_pendidikan = bidan_pendidikan;
    }

    public String getRadiografi() {
        return radiografi;
    }

    public void setRadiografi(String radiografi) {
        this.radiografi = radiografi;
    }

    public String getRefraksionis() {
        return refraksionis;
    }

    public void setRefraksionis(String refraksionis) {
        this.refraksionis = refraksionis;
    }

    public String getRekam_medis() {
        return rekam_medis;
    }

    public void setRekam_medis(String rekam_medis) {
        this.rekam_medis = rekam_medis;
    }

    public String getTransfusi_darah() {
        return transfusi_darah;
    }

    public void setTransfusi_darah(String transfusi_darah) {
        this.transfusi_darah = transfusi_darah;
    }

    public String getKardiovaskular() {
        return kardiovaskular;
    }

    public void setKardiovaskular(String kardiovaskular) {
        this.kardiovaskular = kardiovaskular;
    }

    public String getEpidemiology() {
        return epidemiology;
    }

    public void setEpidemiology(String epidemiology) {
        this.epidemiology = epidemiology;
    }

    public String getPrilaku() {
        return prilaku;
    }

    public void setPrilaku(String prilaku) {
        this.prilaku = prilaku;
    }

    public String getBiostatik() {
        return biostatik;
    }

    public void setBiostatik(String biostatik) {
        this.biostatik = biostatik;
    }

    public String getDietisien() {
        return dietisien;
    }

    public void setDietisien(String dietisien) {
        this.dietisien = dietisien;
    }

    public String getAdmin_kesehatan() {
        return admin_kesehatan;
    }

    public void setAdmin_kesehatan(String admin_kesehatan) {
        this.admin_kesehatan = admin_kesehatan;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPengurus() {
        return pengurus;
    }

    public void setPengurus(String pengurus) {
        this.pengurus = pengurus;
    }

    public String getAnggota() {
        return anggota;
    }

    public void setAnggota(String anggota) {
        this.anggota = anggota;
    }

    public String getId_fmcg_param() {
        return id_fmcg_param;
    }

    public void setId_fmcg_param(String id_fmcg_param) {
        this.id_fmcg_param = id_fmcg_param;
    }

    public String getNama_param() {
        return nama_param;
    }

    public void setNama_param(String nama_param) {
        this.nama_param = nama_param;
    }

    public String getRumus_param() {
        return rumus_param;
    }

    public void setRumus_param(String rumus_param) {
        this.rumus_param = rumus_param;
    }

    public String getKategori_param() {
        return kategori_param;
    }

    public void setKategori_param(String kategori_param) {
        this.kategori_param = kategori_param;
    }

    public String getRata_rata_param() {
        return rata_rata_param;
    }

    public void setRata_rata_param(String rata_rata_param) {
        this.rata_rata_param = rata_rata_param;
    }

    public String getPenjelasan_param() {
        return penjelasan_param;
    }

    public void setPenjelasan_param(String penjelasan_param) {
        this.penjelasan_param = penjelasan_param;
    }

    public String getPenjelasan2_param() {
        return penjelasan2_param;
    }

    public void setPenjelasan2_param(String penjelasan2_param) {
        this.penjelasan2_param = penjelasan2_param;
    }
}
