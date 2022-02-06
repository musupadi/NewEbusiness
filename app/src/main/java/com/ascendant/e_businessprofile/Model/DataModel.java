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

    //Wilayah
    @SerializedName("nama_wilayah_mandiri")
    @Expose
    public String nama_wilayah_mandiri;

    @SerializedName("id_wilayah_mandiri")
    @Expose
    public String id_wilayah_mandiri;

    @SerializedName("wilayah")
    @Expose
    public String wilayah;

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

    @SerializedName("nama_probing")
    @Expose
    public String nama_probing;


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

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

    @SerializedName("tgl_upload_video")
    @Expose
    public String tgl_upload_video;

    @SerializedName("source_video")
    @Expose
    public String source_video;

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

    //Daftar Posting
    @SerializedName("id_post")
    @Expose
    public String id_post;

    @SerializedName("kategori_post")
    @Expose
    public String kategori_post;

    @SerializedName("id_user_mandiri")
    @Expose
    public String id_user_mandiri;

    @SerializedName("judul_post")
    @Expose
    public String judul_post;

    @SerializedName("isi_post")
    @Expose
    public String isi_post;

    @SerializedName("status_post")
    @Expose
    public String status_post;

    @SerializedName("tgl_post")
    @Expose
    public String tgl_post;

    @SerializedName("tgl_last_edit_post")
    @Expose
    public String tgl_last_edit_post;

    @SerializedName("created_at_post")
    @Expose
    public String created_at_post;

    @SerializedName("detail")
    @Expose
    DataModel detail;

    @SerializedName("image")
    @Expose
    List<DataModel> image;

    @SerializedName("id_post_img")
    @Expose
    public String id_post_img;

    @SerializedName("file_img_post")
    @Expose
    public String file_img_post;

    @SerializedName("caption")
    @Expose
    public String caption;

    @SerializedName("komen")
    @Expose
    List<DataModel> komen;

    @SerializedName("id_post_komen")
    @Expose
    public String id_post_komen;

    @SerializedName("isi_komen")
    @Expose
    public String isi_komen;

    @SerializedName("img_komen")
    @Expose
    public String img_komen;

    @SerializedName("tgl_komen")
    @Expose
    public String tgl_komen;

    @SerializedName("tgl_last_edit_komen")
    @Expose
    public String tgl_last_edit_komen;

    @SerializedName("total_sub_komen")
    @Expose
    public String total_sub_komen;

    @SerializedName("sub_komen")
    @Expose
    List<DataModel> sub_komen;

    @SerializedName("id_post_komen_sub")
    @Expose
    public String id_post_komen_sub;

    //Quiz
    @SerializedName("id_quiz")
    @Expose
    public String id_quiz;

    @SerializedName("pg_jawaban")
    @Expose
    public String pg_jawaban;

    @SerializedName("id_quiz_jawaban")
    @Expose
    public String id_quiz_jawaban;

    @SerializedName("isi_jawaban")
    @Expose
    public String isi_jawaban;

    //Mandiri update
    @SerializedName("id_mandiri_update")
    @Expose
    public String id_mandiri_update;

    @SerializedName("kategori_mandiri_update")
    @Expose
    public String kategori_mandiri_update;

    @SerializedName("judul_mandiri_update")
    @Expose
    public String judul_mandiri_update;

    @SerializedName("slug_mandiri_update")
    @Expose
    public String slug_mandiri_update;

    @SerializedName("isi_mandiri_update")
    @Expose
    public String isi_mandiri_update;

    @SerializedName("status_mandiri_update")
    @Expose
    public String status_mandiri_update;

    @SerializedName("cover_mandiri_update")
    @Expose
    public String cover_mandiri_update;

    @SerializedName("link_youtube")
    @Expose
    public String link_youtube;

    @SerializedName("id_mandiri_update_file")
    @Expose
    public String id_mandiri_update_file;

    @SerializedName("link_file_mandiri_update")
    @Expose
    public String link_file_mandiri_update;

    @SerializedName("caption_file")
    @Expose
    public String caption_file;
    //Outlook

    @SerializedName("id_outlook")
    @Expose
    public String id_outlook;

    @SerializedName("nama_outlook")
    @Expose
    public String nama_outlook;

    @SerializedName("kategori_outlook")
    @Expose
    public String kategori_outlook;

    @SerializedName("jenis_outlook")
    @Expose
    public String jenis_outlook;

    @SerializedName("tgl_outlook")
    @Expose
    public String tgl_outlook;

    @SerializedName("link_file_outlook")
    @Expose
    public String link_file_outlook;

    @SerializedName("id_perusahaan_farming")
    @Expose
    public String id_perusahaan_farming;

    @SerializedName("nama_perusahaan")
    @Expose
    public String nama_perusahaan;

    @SerializedName("kategori_perusahaan")
    @Expose
    public String kategori_perusahaan;

    @SerializedName("bidang")
    @Expose
    public String bidang;

    @SerializedName("link_simulator")
    @Expose
    public String link_simulator;

    @SerializedName("kelompok")
    @Expose
    public String kelompok;

    @SerializedName("sektor")
    @Expose
    public String sektor;

    @SerializedName("coming_soon")
    @Expose
    public String coming_soon;

    @SerializedName("id_app_update")
    @Expose
    public String id_app_update;

    @SerializedName("ver")
    @Expose
    public String ver;

    @SerializedName("total")
    @Expose
    public String total;

    @SerializedName("dilihat")
    @Expose
    public String dilihat;

    @SerializedName("judul_notif")
    @Expose
    public String judul_notif;

    @SerializedName("deskripsi_notif")
    @Expose
    public String deskripsi_notif;

    @SerializedName("link_notif")
    @Expose
    public String link_notif;

    //Poin
    @SerializedName("id_poin_hadiah")
    @Expose
    public String id_poin_hadiah;

    @SerializedName("nama_hadiah")
    @Expose
    public String nama_hadiah;

    @SerializedName("poin_dibutuhkan")
    @Expose
    public String poin_dibutuhkan;

    @SerializedName("tgl_poin_didapatkan")
    @Expose
    public String tgl_poin_didapatkan;

    @SerializedName("jenis_riwayat")
    @Expose
    public String jenis_riwayat;

    @SerializedName("deskripsi_poin")
    @Expose
    public String deskripsi_poin;

    @SerializedName("nilai_poin")
    @Expose
    public String nilai_poin;

    @SerializedName("status_plus")
    @Expose
    public String status_plus;

    @SerializedName("status_hadiah")
    @Expose
    public String status_hadiah;

    @SerializedName("status_penukaran")
    @Expose
    public String status_penukaran;

    @SerializedName("tgl_pengajuan")
    @Expose
    public String tgl_pengajuan;

    @SerializedName("tgl_diterima")
    @Expose
    public String tgl_diterima;

    @SerializedName("tgl_ditolak")
    @Expose
    public String tgl_ditolak;

    @SerializedName("alasan_ditolak")
    @Expose
    public String alasan_ditolak;

    //Ask Expert
    @SerializedName("id_ask_expert")
    @Expose
    public String id_ask_expert;

    @SerializedName("id_expert")
    @Expose
    public String id_expert;

    @SerializedName("topik_pertanyaan")
    @Expose
    public String topik_pertanyaan;

    @SerializedName("kategori_ask")
    @Expose
    public String kategori_ask;

    @SerializedName("status_ask")
    @Expose
    public String status_ask;

    @SerializedName("total_balasan")
    @Expose
    public String total_balasan;

    @SerializedName("link_konstruksi_2021")
    @Expose
    public String  link_konstruksi_2021;


    //Old EBusiness
    String id_fmcg_param,nama_param,rumus_param,kategori_param,rata_rata_param,penjelasan_param,penjelasan2_param;
    String id_asosiasi,nilaiperkapita;
    String password,nama,no_telpon,status,level_user;
    String pengurus,anggota;
    List<DataModel> list;
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

    String id_ebook,nama_ebook,tgl_upload_ebook,link_file_ebook,ext_file,link_ebook,mode_ebook;
    String Email,Password;
    String idNavigator,Navigator,ONCLICK;
    String id_persi,nama_persi,alamat_persi,telpon_persi,email_persi;
    String no_quis,category_quis,soal_quis,simulasi,jawaban;
    String total_online,register,status_online,market;
    String market_size,dua,share;
    String id_fmcg_fivec,no_fivec,kategori_fivec,pernyataan_fivec,isi_fivec,nilai_yes,nilai_no,simulasi_fivec;

    String id_regulation,nama_peraturan,jenis_peraturan,kategori_regulation,highlight_regulations,tahun_peraturan,tgl_upload_regulation,link_file_regulation;
    String tgl_registrasi_rs,jenis_rs,
            direktur_rs,latar_pendidikan_direktur_rs,pemilik_rs,
            alamat_rs,provinsi_rs,kabupaten_kota_rs,
            kode_pos_rs,telepon_rs,fax_rs,email_rs,telepon_humas_rs,website_rs,luas_tanah_rs,
            luas_bangunan_rs,no_surat_ijin,tgl_surat_ijin,surat_ijin_dari,sifat_surat_ijin,
            masa_berlaku_ijin,status_penyelenggara,status_akreditasi,tgl_akreditasi,berlaku_sampai_dengan,
            tgl_update,vvip_bed,vip_bed,kelas_1_bed,kelas_2_bed,kelas_3_bed,icu_bed,picu_bed,nicu_bed,tt_bayi_baru_lahir_bed,
            hcu_bed,iccu_bed,igd_bed,tt_di_ruang_operasi_bed,tt_di_ruang_isolasi_bed,dr_umum,dr_sp_og,dr_sp_pd,dr_sp_b,dr_sp_rad,
            dr_sp_rm,dr_sp_an,dr_sp_jp,dr_sp_m,dr_sp_tht,dr_sp_pk,dr_sp_paru,dr_sp_bedah_thoraks,dr_sp_bedah_anak,dr_sp_bedah_orthopedi,
            dr_sp_a,dr_sp_okupasi,dr_sp_urologi,dr_sp_orthopedi,dr_sp_kulit_dan_kelamin,dr_sp_forensik,dr_sp_psikiatri,dr_sp_ofthamologi,
            dr_sp_patologi_anatomi,dr_sp_kes_kejiwaan,dr_sp_saraf,dr_sp_lainnya,dr_sp_bedah_saraf,dr_sp_bedah_plastik,dr_sub_spesialis,dr_gigi,
            dr_gigi_sp_bedah_mulut,dr_gigi_sp_konservasi,dr_gigi_sp_penyakit_mulut,dr_gigi_sp_radiologi,dr_gigi_sp_karang_gigi,dr_gigi_sp_anak,
            dr_gigi_sp_gigi_tiruan,dr_gigi_sp_periodonsia,dr_gigi_sp_lainnya,ners,perawat_bedah,perawat_maternitas,perawat_komunitas,perawat_gigi,
            perawat_anestesi,perawat_anak,perawat_lainnya,bidan_pendidik,bidan_klinik,apoteker,analis_farmasi,radiografer,radioterapis,elektromedis,
            teknisi_gigi,analis_kesehatan,refaksionis,rekam_medik,ortotik,teknisi_transfusi_darah,teknisi_kardiovaskular,epidemiologi,promosi_kesehatan,
            perilaku,kesja,administrasi_kesehatan,biostatistik,reproduksi,informasi_kesehatan,kesmas_lainnya,sanitasi,entomologi,mikrobiologi,kesehatan_lingkungan,
            terapi_wicara,nutrisionis,dietsien,fisioterapi,terapi_okupasi,akupunturis,program_kesehatan,administrasi_keuangan,humas,perencanaan,jaminan_kesehatan,dosen,
            psikologi,pelaporan,informasi_teknologi,hukum,pekarya,perpustakaan,widyaiswara,tenaga_non_kes,meja_operasi,mesin_anestesi,ventilator,inkubator,blue_light,usg,
            x_ray,ct_scan,mri,eeg,ekg,defibrilator,autoclav,rawat_jalan,rawat_inap,igd,bor,alos,toi,ndr,gdr,layanan_unggulan,simrs,ambulan,bank_darah,i_g_dbank;

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

    public String getTotal_online() {
        return total_online;
    }

    public void setTotal_online(String total_online) {
        this.total_online = total_online;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getStatus_online() {
        return status_online;
    }

    public void setStatus_online(String status_online) {
        this.status_online = status_online;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMarket_size() {
        return market_size;
    }

    public void setMarket_size(String market_size) {
        this.market_size = market_size;
    }

    public String getDua() {
        return dua;
    }

    public void setDua(String dua) {
        this.dua = dua;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getId_fmcg_fivec() {
        return id_fmcg_fivec;
    }

    public void setId_fmcg_fivec(String id_fmcg_fivec) {
        this.id_fmcg_fivec = id_fmcg_fivec;
    }

    public String getNo_fivec() {
        return no_fivec;
    }

    public void setNo_fivec(String no_fivec) {
        this.no_fivec = no_fivec;
    }

    public String getKategori_fivec() {
        return kategori_fivec;
    }

    public void setKategori_fivec(String kategori_fivec) {
        this.kategori_fivec = kategori_fivec;
    }

    public String getPernyataan_fivec() {
        return pernyataan_fivec;
    }

    public void setPernyataan_fivec(String pernyataan_fivec) {
        this.pernyataan_fivec = pernyataan_fivec;
    }

    public String getIsi_fivec() {
        return isi_fivec;
    }

    public void setIsi_fivec(String isi_fivec) {
        this.isi_fivec = isi_fivec;
    }

    public String getNilai_yes() {
        return nilai_yes;
    }

    public void setNilai_yes(String nilai_yes) {
        this.nilai_yes = nilai_yes;
    }

    public String getNilai_no() {
        return nilai_no;
    }

    public void setNilai_no(String nilai_no) {
        this.nilai_no = nilai_no;
    }

    public String getSimulasi_fivec() {
        return simulasi_fivec;
    }

    public void setSimulasi_fivec(String simulasi_fivec) {
        this.simulasi_fivec = simulasi_fivec;
    }

    public String getId_ebook() {
        return id_ebook;
    }

    public void setId_ebook(String id_ebook) {
        this.id_ebook = id_ebook;
    }

    public String getNama_ebook() {
        return nama_ebook;
    }

    public void setNama_ebook(String nama_ebook) {
        this.nama_ebook = nama_ebook;
    }

    public String getTgl_upload_ebook() {
        return tgl_upload_ebook;
    }

    public void setTgl_upload_ebook(String tgl_upload_ebook) {
        this.tgl_upload_ebook = tgl_upload_ebook;
    }

    public String getLink_file_ebook() {
        return link_file_ebook;
    }

    public void setLink_file_ebook(String link_file_ebook) {
        this.link_file_ebook = link_file_ebook;
    }

    public String getExt_file() {
        return ext_file;
    }

    public void setExt_file(String ext_file) {
        this.ext_file = ext_file;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getKategori_post() {
        return kategori_post;
    }

    public void setKategori_post(String kategori_post) {
        this.kategori_post = kategori_post;
    }

    public String getId_user_mandiri() {
        return id_user_mandiri;
    }

    public void setId_user_mandiri(String id_user_mandiri) {
        this.id_user_mandiri = id_user_mandiri;
    }

    public String getJudul_post() {
        return judul_post;
    }

    public void setJudul_post(String judul_post) {
        this.judul_post = judul_post;
    }

    public String getIsi_post() {
        return isi_post;
    }

    public void setIsi_post(String isi_post) {
        this.isi_post = isi_post;
    }

    public String getStatus_post() {
        return status_post;
    }

    public void setStatus_post(String status_post) {
        this.status_post = status_post;
    }

    public String getTgl_post() {
        return tgl_post;
    }

    public void setTgl_post(String tgl_post) {
        this.tgl_post = tgl_post;
    }

    public String getTgl_last_edit_post() {
        return tgl_last_edit_post;
    }

    public void setTgl_last_edit_post(String tgl_last_edit_post) {
        this.tgl_last_edit_post = tgl_last_edit_post;
    }

    public String getCreated_at_post() {
        return created_at_post;
    }

    public void setCreated_at_post(String created_at_post) {
        this.created_at_post = created_at_post;
    }

    public DataModel getDetail() {
        return detail;
    }

    public void setDetail(DataModel detail) {
        this.detail = detail;
    }

    public List<DataModel> getImage() {
        return image;
    }

    public void setImage(List<DataModel> image) {
        this.image = image;
    }

    public String getId_post_img() {
        return id_post_img;
    }

    public void setId_post_img(String id_post_img) {
        this.id_post_img = id_post_img;
    }

    public String getFile_img_post() {
        return file_img_post;
    }

    public void setFile_img_post(String file_img_post) {
        this.file_img_post = file_img_post;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<DataModel> getKomen() {
        return komen;
    }

    public void setKomen(List<DataModel> komen) {
        this.komen = komen;
    }

    public String getId_post_komen() {
        return id_post_komen;
    }

    public void setId_post_komen(String id_post_komen) {
        this.id_post_komen = id_post_komen;
    }

    public String getIsi_komen() {
        return isi_komen;
    }

    public void setIsi_komen(String isi_komen) {
        this.isi_komen = isi_komen;
    }

    public String getImg_komen() {
        return img_komen;
    }

    public void setImg_komen(String img_komen) {
        this.img_komen = img_komen;
    }

    public String getTgl_komen() {
        return tgl_komen;
    }

    public void setTgl_komen(String tgl_komen) {
        this.tgl_komen = tgl_komen;
    }

    public String getTgl_last_edit_komen() {
        return tgl_last_edit_komen;
    }

    public void setTgl_last_edit_komen(String tgl_last_edit_komen) {
        this.tgl_last_edit_komen = tgl_last_edit_komen;
    }

    public String getTotal_sub_komen() {
        return total_sub_komen;
    }

    public void setTotal_sub_komen(String total_sub_komen) {
        this.total_sub_komen = total_sub_komen;
    }

    public List<DataModel> getSub_komen() {
        return sub_komen;
    }

    public void setSub_komen(List<DataModel> sub_komen) {
        this.sub_komen = sub_komen;
    }

    public String getId_post_komen_sub() {
        return id_post_komen_sub;
    }

    public void setId_post_komen_sub(String id_post_komen_sub) {
        this.id_post_komen_sub = id_post_komen_sub;
    }

    public String getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(String id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getPg_jawaban() {
        return pg_jawaban;
    }

    public void setPg_jawaban(String pg_jawaban) {
        this.pg_jawaban = pg_jawaban;
    }

    public String getId_quiz_jawaban() {
        return id_quiz_jawaban;
    }

    public void setId_quiz_jawaban(String id_quiz_jawaban) {
        this.id_quiz_jawaban = id_quiz_jawaban;
    }

    public String getIsi_jawaban() {
        return isi_jawaban;
    }

    public void setIsi_jawaban(String isi_jawaban) {
        this.isi_jawaban = isi_jawaban;
    }

    public String getNama_wilayah_mandiri() {
        return nama_wilayah_mandiri;
    }

    public void setNama_wilayah_mandiri(String nama_wilayah_mandiri) {
        this.nama_wilayah_mandiri = nama_wilayah_mandiri;
    }

    public String getId_wilayah_mandiri() {
        return id_wilayah_mandiri;
    }

    public void setId_wilayah_mandiri(String id_wilayah_mandiri) {
        this.id_wilayah_mandiri = id_wilayah_mandiri;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getSource_video() {
        return source_video;
    }

    public void setSource_video(String source_video) {
        this.source_video = source_video;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId_mandiri_update() {
        return id_mandiri_update;
    }

    public void setId_mandiri_update(String id_mandiri_update) {
        this.id_mandiri_update = id_mandiri_update;
    }

    public String getKategori_mandiri_update() {
        return kategori_mandiri_update;
    }

    public void setKategori_mandiri_update(String kategori_mandiri_update) {
        this.kategori_mandiri_update = kategori_mandiri_update;
    }

    public String getJudul_mandiri_update() {
        return judul_mandiri_update;
    }

    public void setJudul_mandiri_update(String judul_mandiri_update) {
        this.judul_mandiri_update = judul_mandiri_update;
    }

    public String getSlug_mandiri_update() {
        return slug_mandiri_update;
    }

    public void setSlug_mandiri_update(String slug_mandiri_update) {
        this.slug_mandiri_update = slug_mandiri_update;
    }

    public String getIsi_mandiri_update() {
        return isi_mandiri_update;
    }

    public void setIsi_mandiri_update(String isi_mandiri_update) {
        this.isi_mandiri_update = isi_mandiri_update;
    }

    public String getStatus_mandiri_update() {
        return status_mandiri_update;
    }

    public void setStatus_mandiri_update(String status_mandiri_update) {
        this.status_mandiri_update = status_mandiri_update;
    }

    public String getCover_mandiri_update() {
        return cover_mandiri_update;
    }

    public void setCover_mandiri_update(String cover_mandiri_update) {
        this.cover_mandiri_update = cover_mandiri_update;
    }

    public String getLink_youtube() {
        return link_youtube;
    }

    public void setLink_youtube(String link_youtube) {
        this.link_youtube = link_youtube;
    }

    public String getId_mandiri_update_file() {
        return id_mandiri_update_file;
    }

    public void setId_mandiri_update_file(String id_mandiri_update_file) {
        this.id_mandiri_update_file = id_mandiri_update_file;
    }

    public String getLink_file_mandiri_update() {
        return link_file_mandiri_update;
    }

    public void setLink_file_mandiri_update(String link_file_mandiri_update) {
        this.link_file_mandiri_update = link_file_mandiri_update;
    }

    public String getNama_probing() {
        return nama_probing;
    }

    public void setNama_probing(String nama_probing) {
        this.nama_probing = nama_probing;
    }

    public String getLink_ebook() {
        return link_ebook;
    }

    public void setLink_ebook(String link_ebook) {
        this.link_ebook = link_ebook;
    }

    public String getMode_ebook() {
        return mode_ebook;
    }

    public void setMode_ebook(String mode_ebook) {
        this.mode_ebook = mode_ebook;
    }

    public String getId_outlook() {
        return id_outlook;
    }

    public void setId_outlook(String id_outlook) {
        this.id_outlook = id_outlook;
    }

    public String getNama_outlook() {
        return nama_outlook;
    }

    public void setNama_outlook(String nama_outlook) {
        this.nama_outlook = nama_outlook;
    }

    public String getKategori_outlook() {
        return kategori_outlook;
    }

    public void setKategori_outlook(String kategori_outlook) {
        this.kategori_outlook = kategori_outlook;
    }

    public String getJenis_outlook() {
        return jenis_outlook;
    }

    public void setJenis_outlook(String jenis_outlook) {
        this.jenis_outlook = jenis_outlook;
    }

    public String getTgl_outlook() {
        return tgl_outlook;
    }

    public void setTgl_outlook(String tgl_outlook) {
        this.tgl_outlook = tgl_outlook;
    }

    public String getLink_file_outlook() {
        return link_file_outlook;
    }

    public void setLink_file_outlook(String link_file_outlook) {
        this.link_file_outlook = link_file_outlook;
    }

    public String getId_perusahaan_farming() {
        return id_perusahaan_farming;
    }

    public void setId_perusahaan_farming(String id_perusahaan_farming) {
        this.id_perusahaan_farming = id_perusahaan_farming;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getKategori_perusahaan() {
        return kategori_perusahaan;
    }

    public void setKategori_perusahaan(String kategori_perusahaan) {
        this.kategori_perusahaan = kategori_perusahaan;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public List<DataModel> getList() {
        return list;
    }

    public void setList(List<DataModel> list) {
        this.list = list;
    }

    public String getTgl_registrasi_rs() {
        return tgl_registrasi_rs;
    }

    public void setTgl_registrasi_rs(String tgl_registrasi_rs) {
        this.tgl_registrasi_rs = tgl_registrasi_rs;
    }

    public String getJenis_rs() {
        return jenis_rs;
    }

    public void setJenis_rs(String jenis_rs) {
        this.jenis_rs = jenis_rs;
    }

    public String getDirektur_rs() {
        return direktur_rs;
    }

    public void setDirektur_rs(String direktur_rs) {
        this.direktur_rs = direktur_rs;
    }

    public String getLatar_pendidikan_direktur_rs() {
        return latar_pendidikan_direktur_rs;
    }

    public void setLatar_pendidikan_direktur_rs(String latar_pendidikan_direktur_rs) {
        this.latar_pendidikan_direktur_rs = latar_pendidikan_direktur_rs;
    }

    public String getPemilik_rs() {
        return pemilik_rs;
    }

    public void setPemilik_rs(String pemilik_rs) {
        this.pemilik_rs = pemilik_rs;
    }

    public String getAlamat_rs() {
        return alamat_rs;
    }

    public void setAlamat_rs(String alamat_rs) {
        this.alamat_rs = alamat_rs;
    }

    public String getProvinsi_rs() {
        return provinsi_rs;
    }

    public void setProvinsi_rs(String provinsi_rs) {
        this.provinsi_rs = provinsi_rs;
    }

    public String getKabupaten_kota_rs() {
        return kabupaten_kota_rs;
    }

    public void setKabupaten_kota_rs(String kabupaten_kota_rs) {
        this.kabupaten_kota_rs = kabupaten_kota_rs;
    }

    public String getKode_pos_rs() {
        return kode_pos_rs;
    }

    public void setKode_pos_rs(String kode_pos_rs) {
        this.kode_pos_rs = kode_pos_rs;
    }

    public String getTelepon_rs() {
        return telepon_rs;
    }

    public void setTelepon_rs(String telepon_rs) {
        this.telepon_rs = telepon_rs;
    }

    public String getFax_rs() {
        return fax_rs;
    }

    public void setFax_rs(String fax_rs) {
        this.fax_rs = fax_rs;
    }

    public String getEmail_rs() {
        return email_rs;
    }

    public void setEmail_rs(String email_rs) {
        this.email_rs = email_rs;
    }

    public String getTelepon_humas_rs() {
        return telepon_humas_rs;
    }

    public void setTelepon_humas_rs(String telepon_humas_rs) {
        this.telepon_humas_rs = telepon_humas_rs;
    }

    public String getWebsite_rs() {
        return website_rs;
    }

    public void setWebsite_rs(String website_rs) {
        this.website_rs = website_rs;
    }

    public String getLuas_tanah_rs() {
        return luas_tanah_rs;
    }

    public void setLuas_tanah_rs(String luas_tanah_rs) {
        this.luas_tanah_rs = luas_tanah_rs;
    }

    public String getLuas_bangunan_rs() {
        return luas_bangunan_rs;
    }

    public void setLuas_bangunan_rs(String luas_bangunan_rs) {
        this.luas_bangunan_rs = luas_bangunan_rs;
    }

    public String getNo_surat_ijin() {
        return no_surat_ijin;
    }

    public void setNo_surat_ijin(String no_surat_ijin) {
        this.no_surat_ijin = no_surat_ijin;
    }

    public String getTgl_surat_ijin() {
        return tgl_surat_ijin;
    }

    public void setTgl_surat_ijin(String tgl_surat_ijin) {
        this.tgl_surat_ijin = tgl_surat_ijin;
    }

    public String getSurat_ijin_dari() {
        return surat_ijin_dari;
    }

    public void setSurat_ijin_dari(String surat_ijin_dari) {
        this.surat_ijin_dari = surat_ijin_dari;
    }

    public String getSifat_surat_ijin() {
        return sifat_surat_ijin;
    }

    public void setSifat_surat_ijin(String sifat_surat_ijin) {
        this.sifat_surat_ijin = sifat_surat_ijin;
    }

    public String getMasa_berlaku_ijin() {
        return masa_berlaku_ijin;
    }

    public void setMasa_berlaku_ijin(String masa_berlaku_ijin) {
        this.masa_berlaku_ijin = masa_berlaku_ijin;
    }

    public String getStatus_penyelenggara() {
        return status_penyelenggara;
    }

    public void setStatus_penyelenggara(String status_penyelenggara) {
        this.status_penyelenggara = status_penyelenggara;
    }

    public String getStatus_akreditasi() {
        return status_akreditasi;
    }

    public void setStatus_akreditasi(String status_akreditasi) {
        this.status_akreditasi = status_akreditasi;
    }

    public String getTgl_akreditasi() {
        return tgl_akreditasi;
    }

    public void setTgl_akreditasi(String tgl_akreditasi) {
        this.tgl_akreditasi = tgl_akreditasi;
    }

    public String getBerlaku_sampai_dengan() {
        return berlaku_sampai_dengan;
    }

    public void setBerlaku_sampai_dengan(String berlaku_sampai_dengan) {
        this.berlaku_sampai_dengan = berlaku_sampai_dengan;
    }

    public String getTgl_update() {
        return tgl_update;
    }

    public void setTgl_update(String tgl_update) {
        this.tgl_update = tgl_update;
    }

    public String getVvip_bed() {
        return vvip_bed;
    }

    public void setVvip_bed(String vvip_bed) {
        this.vvip_bed = vvip_bed;
    }

    public String getVip_bed() {
        return vip_bed;
    }

    public void setVip_bed(String vip_bed) {
        this.vip_bed = vip_bed;
    }

    public String getKelas_1_bed() {
        return kelas_1_bed;
    }

    public void setKelas_1_bed(String kelas_1_bed) {
        this.kelas_1_bed = kelas_1_bed;
    }

    public String getKelas_2_bed() {
        return kelas_2_bed;
    }

    public void setKelas_2_bed(String kelas_2_bed) {
        this.kelas_2_bed = kelas_2_bed;
    }

    public String getKelas_3_bed() {
        return kelas_3_bed;
    }

    public void setKelas_3_bed(String kelas_3_bed) {
        this.kelas_3_bed = kelas_3_bed;
    }

    public String getIcu_bed() {
        return icu_bed;
    }

    public void setIcu_bed(String icu_bed) {
        this.icu_bed = icu_bed;
    }

    public String getPicu_bed() {
        return picu_bed;
    }

    public void setPicu_bed(String picu_bed) {
        this.picu_bed = picu_bed;
    }

    public String getNicu_bed() {
        return nicu_bed;
    }

    public void setNicu_bed(String nicu_bed) {
        this.nicu_bed = nicu_bed;
    }

    public String getTt_bayi_baru_lahir_bed() {
        return tt_bayi_baru_lahir_bed;
    }

    public void setTt_bayi_baru_lahir_bed(String tt_bayi_baru_lahir_bed) {
        this.tt_bayi_baru_lahir_bed = tt_bayi_baru_lahir_bed;
    }

    public String getHcu_bed() {
        return hcu_bed;
    }

    public void setHcu_bed(String hcu_bed) {
        this.hcu_bed = hcu_bed;
    }

    public String getIccu_bed() {
        return iccu_bed;
    }

    public void setIccu_bed(String iccu_bed) {
        this.iccu_bed = iccu_bed;
    }

    public String getIgd_bed() {
        return igd_bed;
    }

    public void setIgd_bed(String igd_bed) {
        this.igd_bed = igd_bed;
    }

    public String getTt_di_ruang_operasi_bed() {
        return tt_di_ruang_operasi_bed;
    }

    public void setTt_di_ruang_operasi_bed(String tt_di_ruang_operasi_bed) {
        this.tt_di_ruang_operasi_bed = tt_di_ruang_operasi_bed;
    }

    public String getTt_di_ruang_isolasi_bed() {
        return tt_di_ruang_isolasi_bed;
    }

    public void setTt_di_ruang_isolasi_bed(String tt_di_ruang_isolasi_bed) {
        this.tt_di_ruang_isolasi_bed = tt_di_ruang_isolasi_bed;
    }

    public String getDr_umum() {
        return dr_umum;
    }

    public void setDr_umum(String dr_umum) {
        this.dr_umum = dr_umum;
    }

    public String getDr_sp_og() {
        return dr_sp_og;
    }

    public void setDr_sp_og(String dr_sp_og) {
        this.dr_sp_og = dr_sp_og;
    }

    public String getDr_sp_pd() {
        return dr_sp_pd;
    }

    public void setDr_sp_pd(String dr_sp_pd) {
        this.dr_sp_pd = dr_sp_pd;
    }

    public String getDr_sp_b() {
        return dr_sp_b;
    }

    public void setDr_sp_b(String dr_sp_b) {
        this.dr_sp_b = dr_sp_b;
    }

    public String getDr_sp_rad() {
        return dr_sp_rad;
    }

    public void setDr_sp_rad(String dr_sp_rad) {
        this.dr_sp_rad = dr_sp_rad;
    }

    public String getDr_sp_rm() {
        return dr_sp_rm;
    }

    public void setDr_sp_rm(String dr_sp_rm) {
        this.dr_sp_rm = dr_sp_rm;
    }

    public String getDr_sp_an() {
        return dr_sp_an;
    }

    public void setDr_sp_an(String dr_sp_an) {
        this.dr_sp_an = dr_sp_an;
    }

    public String getDr_sp_jp() {
        return dr_sp_jp;
    }

    public void setDr_sp_jp(String dr_sp_jp) {
        this.dr_sp_jp = dr_sp_jp;
    }

    public String getDr_sp_m() {
        return dr_sp_m;
    }

    public void setDr_sp_m(String dr_sp_m) {
        this.dr_sp_m = dr_sp_m;
    }

    public String getDr_sp_tht() {
        return dr_sp_tht;
    }

    public void setDr_sp_tht(String dr_sp_tht) {
        this.dr_sp_tht = dr_sp_tht;
    }

    public String getDr_sp_pk() {
        return dr_sp_pk;
    }

    public void setDr_sp_pk(String dr_sp_pk) {
        this.dr_sp_pk = dr_sp_pk;
    }

    public String getDr_sp_paru() {
        return dr_sp_paru;
    }

    public void setDr_sp_paru(String dr_sp_paru) {
        this.dr_sp_paru = dr_sp_paru;
    }

    public String getDr_sp_bedah_thoraks() {
        return dr_sp_bedah_thoraks;
    }

    public void setDr_sp_bedah_thoraks(String dr_sp_bedah_thoraks) {
        this.dr_sp_bedah_thoraks = dr_sp_bedah_thoraks;
    }

    public String getDr_sp_bedah_anak() {
        return dr_sp_bedah_anak;
    }

    public void setDr_sp_bedah_anak(String dr_sp_bedah_anak) {
        this.dr_sp_bedah_anak = dr_sp_bedah_anak;
    }

    public String getDr_sp_bedah_orthopedi() {
        return dr_sp_bedah_orthopedi;
    }

    public void setDr_sp_bedah_orthopedi(String dr_sp_bedah_orthopedi) {
        this.dr_sp_bedah_orthopedi = dr_sp_bedah_orthopedi;
    }

    public String getDr_sp_a() {
        return dr_sp_a;
    }

    public void setDr_sp_a(String dr_sp_a) {
        this.dr_sp_a = dr_sp_a;
    }

    public String getDr_sp_okupasi() {
        return dr_sp_okupasi;
    }

    public void setDr_sp_okupasi(String dr_sp_okupasi) {
        this.dr_sp_okupasi = dr_sp_okupasi;
    }

    public String getDr_sp_urologi() {
        return dr_sp_urologi;
    }

    public void setDr_sp_urologi(String dr_sp_urologi) {
        this.dr_sp_urologi = dr_sp_urologi;
    }

    public String getDr_sp_orthopedi() {
        return dr_sp_orthopedi;
    }

    public void setDr_sp_orthopedi(String dr_sp_orthopedi) {
        this.dr_sp_orthopedi = dr_sp_orthopedi;
    }

    public String getDr_sp_kulit_dan_kelamin() {
        return dr_sp_kulit_dan_kelamin;
    }

    public void setDr_sp_kulit_dan_kelamin(String dr_sp_kulit_dan_kelamin) {
        this.dr_sp_kulit_dan_kelamin = dr_sp_kulit_dan_kelamin;
    }

    public String getDr_sp_forensik() {
        return dr_sp_forensik;
    }

    public void setDr_sp_forensik(String dr_sp_forensik) {
        this.dr_sp_forensik = dr_sp_forensik;
    }

    public String getDr_sp_psikiatri() {
        return dr_sp_psikiatri;
    }

    public void setDr_sp_psikiatri(String dr_sp_psikiatri) {
        this.dr_sp_psikiatri = dr_sp_psikiatri;
    }

    public String getDr_sp_ofthamologi() {
        return dr_sp_ofthamologi;
    }

    public void setDr_sp_ofthamologi(String dr_sp_ofthamologi) {
        this.dr_sp_ofthamologi = dr_sp_ofthamologi;
    }

    public String getDr_sp_patologi_anatomi() {
        return dr_sp_patologi_anatomi;
    }

    public void setDr_sp_patologi_anatomi(String dr_sp_patologi_anatomi) {
        this.dr_sp_patologi_anatomi = dr_sp_patologi_anatomi;
    }

    public String getDr_sp_kes_kejiwaan() {
        return dr_sp_kes_kejiwaan;
    }

    public void setDr_sp_kes_kejiwaan(String dr_sp_kes_kejiwaan) {
        this.dr_sp_kes_kejiwaan = dr_sp_kes_kejiwaan;
    }

    public String getDr_sp_saraf() {
        return dr_sp_saraf;
    }

    public void setDr_sp_saraf(String dr_sp_saraf) {
        this.dr_sp_saraf = dr_sp_saraf;
    }

    public String getDr_sp_lainnya() {
        return dr_sp_lainnya;
    }

    public void setDr_sp_lainnya(String dr_sp_lainnya) {
        this.dr_sp_lainnya = dr_sp_lainnya;
    }

    public String getDr_sp_bedah_saraf() {
        return dr_sp_bedah_saraf;
    }

    public void setDr_sp_bedah_saraf(String dr_sp_bedah_saraf) {
        this.dr_sp_bedah_saraf = dr_sp_bedah_saraf;
    }

    public String getDr_sp_bedah_plastik() {
        return dr_sp_bedah_plastik;
    }

    public void setDr_sp_bedah_plastik(String dr_sp_bedah_plastik) {
        this.dr_sp_bedah_plastik = dr_sp_bedah_plastik;
    }

    public String getDr_sub_spesialis() {
        return dr_sub_spesialis;
    }

    public void setDr_sub_spesialis(String dr_sub_spesialis) {
        this.dr_sub_spesialis = dr_sub_spesialis;
    }

    public String getDr_gigi() {
        return dr_gigi;
    }

    public void setDr_gigi(String dr_gigi) {
        this.dr_gigi = dr_gigi;
    }

    public String getDr_gigi_sp_bedah_mulut() {
        return dr_gigi_sp_bedah_mulut;
    }

    public void setDr_gigi_sp_bedah_mulut(String dr_gigi_sp_bedah_mulut) {
        this.dr_gigi_sp_bedah_mulut = dr_gigi_sp_bedah_mulut;
    }

    public String getDr_gigi_sp_konservasi() {
        return dr_gigi_sp_konservasi;
    }

    public void setDr_gigi_sp_konservasi(String dr_gigi_sp_konservasi) {
        this.dr_gigi_sp_konservasi = dr_gigi_sp_konservasi;
    }

    public String getDr_gigi_sp_penyakit_mulut() {
        return dr_gigi_sp_penyakit_mulut;
    }

    public void setDr_gigi_sp_penyakit_mulut(String dr_gigi_sp_penyakit_mulut) {
        this.dr_gigi_sp_penyakit_mulut = dr_gigi_sp_penyakit_mulut;
    }

    public String getDr_gigi_sp_radiologi() {
        return dr_gigi_sp_radiologi;
    }

    public void setDr_gigi_sp_radiologi(String dr_gigi_sp_radiologi) {
        this.dr_gigi_sp_radiologi = dr_gigi_sp_radiologi;
    }

    public String getDr_gigi_sp_karang_gigi() {
        return dr_gigi_sp_karang_gigi;
    }

    public void setDr_gigi_sp_karang_gigi(String dr_gigi_sp_karang_gigi) {
        this.dr_gigi_sp_karang_gigi = dr_gigi_sp_karang_gigi;
    }

    public String getDr_gigi_sp_anak() {
        return dr_gigi_sp_anak;
    }

    public void setDr_gigi_sp_anak(String dr_gigi_sp_anak) {
        this.dr_gigi_sp_anak = dr_gigi_sp_anak;
    }

    public String getDr_gigi_sp_gigi_tiruan() {
        return dr_gigi_sp_gigi_tiruan;
    }

    public void setDr_gigi_sp_gigi_tiruan(String dr_gigi_sp_gigi_tiruan) {
        this.dr_gigi_sp_gigi_tiruan = dr_gigi_sp_gigi_tiruan;
    }

    public String getDr_gigi_sp_periodonsia() {
        return dr_gigi_sp_periodonsia;
    }

    public void setDr_gigi_sp_periodonsia(String dr_gigi_sp_periodonsia) {
        this.dr_gigi_sp_periodonsia = dr_gigi_sp_periodonsia;
    }

    public String getDr_gigi_sp_lainnya() {
        return dr_gigi_sp_lainnya;
    }

    public void setDr_gigi_sp_lainnya(String dr_gigi_sp_lainnya) {
        this.dr_gigi_sp_lainnya = dr_gigi_sp_lainnya;
    }

    public String getNers() {
        return ners;
    }

    public void setNers(String ners) {
        this.ners = ners;
    }

    public String getPerawat_bedah() {
        return perawat_bedah;
    }

    public void setPerawat_bedah(String perawat_bedah) {
        this.perawat_bedah = perawat_bedah;
    }

    public String getPerawat_maternitas() {
        return perawat_maternitas;
    }

    public void setPerawat_maternitas(String perawat_maternitas) {
        this.perawat_maternitas = perawat_maternitas;
    }

    public String getPerawat_komunitas() {
        return perawat_komunitas;
    }

    public void setPerawat_komunitas(String perawat_komunitas) {
        this.perawat_komunitas = perawat_komunitas;
    }

    public String getPerawat_gigi() {
        return perawat_gigi;
    }

    public void setPerawat_gigi(String perawat_gigi) {
        this.perawat_gigi = perawat_gigi;
    }

    public String getPerawat_anestesi() {
        return perawat_anestesi;
    }

    public void setPerawat_anestesi(String perawat_anestesi) {
        this.perawat_anestesi = perawat_anestesi;
    }

    public String getPerawat_anak() {
        return perawat_anak;
    }

    public void setPerawat_anak(String perawat_anak) {
        this.perawat_anak = perawat_anak;
    }

    public String getPerawat_lainnya() {
        return perawat_lainnya;
    }

    public void setPerawat_lainnya(String perawat_lainnya) {
        this.perawat_lainnya = perawat_lainnya;
    }

    public String getBidan_pendidik() {
        return bidan_pendidik;
    }

    public void setBidan_pendidik(String bidan_pendidik) {
        this.bidan_pendidik = bidan_pendidik;
    }

    public String getBidan_klinik() {
        return bidan_klinik;
    }

    public void setBidan_klinik(String bidan_klinik) {
        this.bidan_klinik = bidan_klinik;
    }

    public String getApoteker() {
        return apoteker;
    }

    public void setApoteker(String apoteker) {
        this.apoteker = apoteker;
    }

    public String getAnalis_farmasi() {
        return analis_farmasi;
    }

    public void setAnalis_farmasi(String analis_farmasi) {
        this.analis_farmasi = analis_farmasi;
    }

    public String getRadiografer() {
        return radiografer;
    }

    public void setRadiografer(String radiografer) {
        this.radiografer = radiografer;
    }

    public String getRadioterapis() {
        return radioterapis;
    }

    public void setRadioterapis(String radioterapis) {
        this.radioterapis = radioterapis;
    }

    public String getElektromedis() {
        return elektromedis;
    }

    public void setElektromedis(String elektromedis) {
        this.elektromedis = elektromedis;
    }

    public String getTeknisi_gigi() {
        return teknisi_gigi;
    }

    public void setTeknisi_gigi(String teknisi_gigi) {
        this.teknisi_gigi = teknisi_gigi;
    }

    public String getAnalis_kesehatan() {
        return analis_kesehatan;
    }

    public void setAnalis_kesehatan(String analis_kesehatan) {
        this.analis_kesehatan = analis_kesehatan;
    }

    public String getRefaksionis() {
        return refaksionis;
    }

    public void setRefaksionis(String refaksionis) {
        this.refaksionis = refaksionis;
    }

    public String getRekam_medik() {
        return rekam_medik;
    }

    public void setRekam_medik(String rekam_medik) {
        this.rekam_medik = rekam_medik;
    }

    public String getOrtotik() {
        return ortotik;
    }

    public void setOrtotik(String ortotik) {
        this.ortotik = ortotik;
    }

    public String getTeknisi_transfusi_darah() {
        return teknisi_transfusi_darah;
    }

    public void setTeknisi_transfusi_darah(String teknisi_transfusi_darah) {
        this.teknisi_transfusi_darah = teknisi_transfusi_darah;
    }

    public String getTeknisi_kardiovaskular() {
        return teknisi_kardiovaskular;
    }

    public void setTeknisi_kardiovaskular(String teknisi_kardiovaskular) {
        this.teknisi_kardiovaskular = teknisi_kardiovaskular;
    }

    public String getEpidemiologi() {
        return epidemiologi;
    }

    public void setEpidemiologi(String epidemiologi) {
        this.epidemiologi = epidemiologi;
    }

    public String getPromosi_kesehatan() {
        return promosi_kesehatan;
    }

    public void setPromosi_kesehatan(String promosi_kesehatan) {
        this.promosi_kesehatan = promosi_kesehatan;
    }

    public String getPerilaku() {
        return perilaku;
    }

    public void setPerilaku(String perilaku) {
        this.perilaku = perilaku;
    }

    public String getKesja() {
        return kesja;
    }

    public void setKesja(String kesja) {
        this.kesja = kesja;
    }

    public String getAdministrasi_kesehatan() {
        return administrasi_kesehatan;
    }

    public void setAdministrasi_kesehatan(String administrasi_kesehatan) {
        this.administrasi_kesehatan = administrasi_kesehatan;
    }

    public String getBiostatistik() {
        return biostatistik;
    }

    public void setBiostatistik(String biostatistik) {
        this.biostatistik = biostatistik;
    }

    public String getReproduksi() {
        return reproduksi;
    }

    public void setReproduksi(String reproduksi) {
        this.reproduksi = reproduksi;
    }

    public String getInformasi_kesehatan() {
        return informasi_kesehatan;
    }

    public void setInformasi_kesehatan(String informasi_kesehatan) {
        this.informasi_kesehatan = informasi_kesehatan;
    }

    public String getKesmas_lainnya() {
        return kesmas_lainnya;
    }

    public void setKesmas_lainnya(String kesmas_lainnya) {
        this.kesmas_lainnya = kesmas_lainnya;
    }

    public String getSanitasi() {
        return sanitasi;
    }

    public void setSanitasi(String sanitasi) {
        this.sanitasi = sanitasi;
    }

    public String getEntomologi() {
        return entomologi;
    }

    public void setEntomologi(String entomologi) {
        this.entomologi = entomologi;
    }

    public String getMikrobiologi() {
        return mikrobiologi;
    }

    public void setMikrobiologi(String mikrobiologi) {
        this.mikrobiologi = mikrobiologi;
    }

    public String getKesehatan_lingkungan() {
        return kesehatan_lingkungan;
    }

    public void setKesehatan_lingkungan(String kesehatan_lingkungan) {
        this.kesehatan_lingkungan = kesehatan_lingkungan;
    }

    public String getTerapi_wicara() {
        return terapi_wicara;
    }

    public void setTerapi_wicara(String terapi_wicara) {
        this.terapi_wicara = terapi_wicara;
    }

    public String getNutrisionis() {
        return nutrisionis;
    }

    public void setNutrisionis(String nutrisionis) {
        this.nutrisionis = nutrisionis;
    }

    public String getDietsien() {
        return dietsien;
    }

    public void setDietsien(String dietsien) {
        this.dietsien = dietsien;
    }

    public String getFisioterapi() {
        return fisioterapi;
    }

    public void setFisioterapi(String fisioterapi) {
        this.fisioterapi = fisioterapi;
    }

    public String getTerapi_okupasi() {
        return terapi_okupasi;
    }

    public void setTerapi_okupasi(String terapi_okupasi) {
        this.terapi_okupasi = terapi_okupasi;
    }

    public String getAkupunturis() {
        return akupunturis;
    }

    public void setAkupunturis(String akupunturis) {
        this.akupunturis = akupunturis;
    }

    public String getProgram_kesehatan() {
        return program_kesehatan;
    }

    public void setProgram_kesehatan(String program_kesehatan) {
        this.program_kesehatan = program_kesehatan;
    }

    public String getAdministrasi_keuangan() {
        return administrasi_keuangan;
    }

    public void setAdministrasi_keuangan(String administrasi_keuangan) {
        this.administrasi_keuangan = administrasi_keuangan;
    }

    public String getHumas() {
        return humas;
    }

    public void setHumas(String humas) {
        this.humas = humas;
    }

    public String getPerencanaan() {
        return perencanaan;
    }

    public void setPerencanaan(String perencanaan) {
        this.perencanaan = perencanaan;
    }

    public String getJaminan_kesehatan() {
        return jaminan_kesehatan;
    }

    public void setJaminan_kesehatan(String jaminan_kesehatan) {
        this.jaminan_kesehatan = jaminan_kesehatan;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getPsikologi() {
        return psikologi;
    }

    public void setPsikologi(String psikologi) {
        this.psikologi = psikologi;
    }

    public String getPelaporan() {
        return pelaporan;
    }

    public void setPelaporan(String pelaporan) {
        this.pelaporan = pelaporan;
    }

    public String getInformasi_teknologi() {
        return informasi_teknologi;
    }

    public void setInformasi_teknologi(String informasi_teknologi) {
        this.informasi_teknologi = informasi_teknologi;
    }

    public String getHukum() {
        return hukum;
    }

    public void setHukum(String hukum) {
        this.hukum = hukum;
    }

    public String getPekarya() {
        return pekarya;
    }

    public void setPekarya(String pekarya) {
        this.pekarya = pekarya;
    }

    public String getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(String perpustakaan) {
        this.perpustakaan = perpustakaan;
    }

    public String getWidyaiswara() {
        return widyaiswara;
    }

    public void setWidyaiswara(String widyaiswara) {
        this.widyaiswara = widyaiswara;
    }

    public String getTenaga_non_kes() {
        return tenaga_non_kes;
    }

    public void setTenaga_non_kes(String tenaga_non_kes) {
        this.tenaga_non_kes = tenaga_non_kes;
    }

    public String getMeja_operasi() {
        return meja_operasi;
    }

    public void setMeja_operasi(String meja_operasi) {
        this.meja_operasi = meja_operasi;
    }

    public String getMesin_anestesi() {
        return mesin_anestesi;
    }

    public void setMesin_anestesi(String mesin_anestesi) {
        this.mesin_anestesi = mesin_anestesi;
    }

    public String getVentilator() {
        return ventilator;
    }

    public void setVentilator(String ventilator) {
        this.ventilator = ventilator;
    }

    public String getInkubator() {
        return inkubator;
    }

    public void setInkubator(String inkubator) {
        this.inkubator = inkubator;
    }

    public String getBlue_light() {
        return blue_light;
    }

    public void setBlue_light(String blue_light) {
        this.blue_light = blue_light;
    }

    public String getUsg() {
        return usg;
    }

    public void setUsg(String usg) {
        this.usg = usg;
    }

    public String getX_ray() {
        return x_ray;
    }

    public void setX_ray(String x_ray) {
        this.x_ray = x_ray;
    }

    public String getCt_scan() {
        return ct_scan;
    }

    public void setCt_scan(String ct_scan) {
        this.ct_scan = ct_scan;
    }

    public String getMri() {
        return mri;
    }

    public void setMri(String mri) {
        this.mri = mri;
    }

    public String getEeg() {
        return eeg;
    }

    public void setEeg(String eeg) {
        this.eeg = eeg;
    }

    public String getEkg() {
        return ekg;
    }

    public void setEkg(String ekg) {
        this.ekg = ekg;
    }

    public String getDefibrilator() {
        return defibrilator;
    }

    public void setDefibrilator(String defibrilator) {
        this.defibrilator = defibrilator;
    }

    public String getAutoclav() {
        return autoclav;
    }

    public void setAutoclav(String autoclav) {
        this.autoclav = autoclav;
    }

    public String getRawat_jalan() {
        return rawat_jalan;
    }

    public void setRawat_jalan(String rawat_jalan) {
        this.rawat_jalan = rawat_jalan;
    }

    public String getRawat_inap() {
        return rawat_inap;
    }

    public void setRawat_inap(String rawat_inap) {
        this.rawat_inap = rawat_inap;
    }

    public String getIgd() {
        return igd;
    }

    public void setIgd(String igd) {
        this.igd = igd;
    }

    public String getBor() {
        return bor;
    }

    public void setBor(String bor) {
        this.bor = bor;
    }

    public String getAlos() {
        return alos;
    }

    public void setAlos(String alos) {
        this.alos = alos;
    }

    public String getToi() {
        return toi;
    }

    public void setToi(String toi) {
        this.toi = toi;
    }

    public String getNdr() {
        return ndr;
    }

    public void setNdr(String ndr) {
        this.ndr = ndr;
    }

    public String getGdr() {
        return gdr;
    }

    public void setGdr(String gdr) {
        this.gdr = gdr;
    }

    public String getLayanan_unggulan() {
        return layanan_unggulan;
    }

    public void setLayanan_unggulan(String layanan_unggulan) {
        this.layanan_unggulan = layanan_unggulan;
    }

    public String getSimrs() {
        return simrs;
    }

    public void setSimrs(String simrs) {
        this.simrs = simrs;
    }

    public String getAmbulan() {
        return ambulan;
    }

    public void setAmbulan(String ambulan) {
        this.ambulan = ambulan;
    }

    public String getBank_darah() {
        return bank_darah;
    }

    public void setBank_darah(String bank_darah) {
        this.bank_darah = bank_darah;
    }

    public String getI_g_dbank() {
        return i_g_dbank;
    }

    public void setI_g_dbank(String i_g_dbank) {
        this.i_g_dbank = i_g_dbank;
    }

    public String getLink_simulator() {
        return link_simulator;
    }

    public void setLink_simulator(String link_simulator) {
        this.link_simulator = link_simulator;
    }

    public String getKelompok() {
        return kelompok;
    }

    public void setKelompok(String kelompok) {
        this.kelompok = kelompok;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public String getComing_soon() {
        return coming_soon;
    }

    public void setComing_soon(String coming_soon) {
        this.coming_soon = coming_soon;
    }


    public String getId_app_update() {
        return id_app_update;
    }

    public void setId_app_update(String id_app_update) {
        this.id_app_update = id_app_update;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getCaption_file() {
        return caption_file;
    }

    public void setCaption_file(String caption_file) {
        this.caption_file = caption_file;
    }


    public String getId_poin_hadiah() {
        return id_poin_hadiah;
    }

    public void setId_poin_hadiah(String id_poin_hadiah) {
        this.id_poin_hadiah = id_poin_hadiah;
    }

    public String getNama_hadiah() {
        return nama_hadiah;
    }

    public void setNama_hadiah(String nama_hadiah) {
        this.nama_hadiah = nama_hadiah;
    }

    public String getPoin_dibutuhkan() {
        return poin_dibutuhkan;
    }

    public void setPoin_dibutuhkan(String poin_dibutuhkan) {
        this.poin_dibutuhkan = poin_dibutuhkan;
    }

    public String getStatus_hadiah() {
        return status_hadiah;
    }

    public void setStatus_hadiah(String status_hadiah) {
        this.status_hadiah = status_hadiah;
    }

    public String getTgl_poin_didapatkan() {
        return tgl_poin_didapatkan;
    }

    public void setTgl_poin_didapatkan(String tgl_poin_didapatkan) {
        this.tgl_poin_didapatkan = tgl_poin_didapatkan;
    }

    public String getDeskripsi_poin() {
        return deskripsi_poin;
    }

    public void setDeskripsi_poin(String deskripsi_poin) {
        this.deskripsi_poin = deskripsi_poin;
    }

    public String getNilai_poin() {
        return nilai_poin;
    }

    public void setNilai_poin(String nilai_poin) {
        this.nilai_poin = nilai_poin;
    }

    public String getStatus_plus() {
        return status_plus;
    }

    public void setStatus_plus(String status_plus) {
        this.status_plus = status_plus;
    }

    public String getStatus_penukaran() {
        return status_penukaran;
    }

    public void setStatus_penukaran(String status_penukaran) {
        this.status_penukaran = status_penukaran;
    }

    public String getTgl_pengajuan() {
        return tgl_pengajuan;
    }

    public void setTgl_pengajuan(String tgl_pengajuan) {
        this.tgl_pengajuan = tgl_pengajuan;
    }

    public String getTgl_diterima() {
        return tgl_diterima;
    }

    public void setTgl_diterima(String tgl_diterima) {
        this.tgl_diterima = tgl_diterima;
    }

    public String getTgl_ditolak() {
        return tgl_ditolak;
    }

    public void setTgl_ditolak(String tgl_ditolak) {
        this.tgl_ditolak = tgl_ditolak;
    }

    public String getAlasan_ditolak() {
        return alasan_ditolak;
    }

    public void setAlasan_ditolak(String alasan_ditolak) {
        this.alasan_ditolak = alasan_ditolak;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDilihat() {
        return dilihat;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }

    public String getDeskripsi_notif() {
        return deskripsi_notif;
    }

    public void setDeskripsi_notif(String deskripsi_notif) {
        this.deskripsi_notif = deskripsi_notif;
    }

    public String getLink_notif() {
        return link_notif;
    }

    public void setLink_notif(String link_notif) {
        this.link_notif = link_notif;
    }

    public String getId_ask_expert() {
        return id_ask_expert;
    }

    public void setId_ask_expert(String id_ask_expert) {
        this.id_ask_expert = id_ask_expert;
    }

    public String getId_expert() {
        return id_expert;
    }

    public void setId_expert(String id_expert) {
        this.id_expert = id_expert;
    }

    public String getTopik_pertanyaan() {
        return topik_pertanyaan;
    }

    public void setTopik_pertanyaan(String topik_pertanyaan) {
        this.topik_pertanyaan = topik_pertanyaan;
    }

    public String getKategori_ask() {
        return kategori_ask;
    }

    public void setKategori_ask(String kategori_ask) {
        this.kategori_ask = kategori_ask;
    }

    public String getStatus_ask() {
        return status_ask;
    }

    public void setStatus_ask(String status_ask) {
        this.status_ask = status_ask;
    }

    public String getTotal_balasan() {
        return total_balasan;
    }

    public void setTotal_balasan(String total_balasan) {
        this.total_balasan = total_balasan;
    }

    public String getJudul_notif() {
        return judul_notif;
    }

    public void setJudul_notif(String judul_notif) {
        this.judul_notif = judul_notif;
    }

    public String getJenis_riwayat() {
        return jenis_riwayat;
    }

    public void setJenis_riwayat(String jenis_riwayat) {
        this.jenis_riwayat = jenis_riwayat;
    }

    public String getLink_konstruksi_2021() {
        return link_konstruksi_2021;
    }

    public void setLink_konstruksi_2021(String link_konstruksi_2021) {
        this.link_konstruksi_2021 = link_konstruksi_2021;
    }

    public String getId_regulation() {
        return id_regulation;
    }

    public void setId_regulation(String id_regulation) {
        this.id_regulation = id_regulation;
    }

    public String getNama_peraturan() {
        return nama_peraturan;
    }

    public void setNama_peraturan(String nama_peraturan) {
        this.nama_peraturan = nama_peraturan;
    }

    public String getJenis_peraturan() {
        return jenis_peraturan;
    }

    public void setJenis_peraturan(String jenis_peraturan) {
        this.jenis_peraturan = jenis_peraturan;
    }

    public String getKategori_regulation() {
        return kategori_regulation;
    }

    public void setKategori_regulation(String kategori_regulation) {
        this.kategori_regulation = kategori_regulation;
    }

    public String getHighlight_regulations() {
        return highlight_regulations;
    }

    public void setHighlight_regulations(String highlight_regulations) {
        this.highlight_regulations = highlight_regulations;
    }

    public String getTahun_peraturan() {
        return tahun_peraturan;
    }

    public void setTahun_peraturan(String tahun_peraturan) {
        this.tahun_peraturan = tahun_peraturan;
    }

    public String getTgl_upload_regulation() {
        return tgl_upload_regulation;
    }

    public void setTgl_upload_regulation(String tgl_upload_regulation) {
        this.tgl_upload_regulation = tgl_upload_regulation;
    }

    public String getLink_file_regulation() {
        return link_file_regulation;
    }

    public void setLink_file_regulation(String link_file_regulation) {
        this.link_file_regulation = link_file_regulation;
    }
}
