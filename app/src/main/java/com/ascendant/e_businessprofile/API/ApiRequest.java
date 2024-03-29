package com.ascendant.e_businessprofile.API;

import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.RegulationFMCGModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseDataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.ResponseQuiz;
import com.ascendant.e_businessprofile.Model.ResponseString;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.Perusahaan;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.Probing;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.ResponseKMK;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.RumusKMK;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("regulations/list_fmcg")
    Call<RegulationFMCGModel> RegulationFMCG(@Header("Authorization") String authHeader,
                                             @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("open_ebook")
    Call<ResponseObject> OpenEbook(@Field("token") String token);


    @FormUrlEncoded
    @POST("suratapi/simulator")
    Call<ResponseObject> SimulasiAnalisisKinerjaRS(@Header("Authorization") String authHeader,
                                                  @Field("type") String type,
                                                  @Field("1") String one,
                                                  @Field("2") String two,
                                                  @Field("3") String three,
                                                  @Field("4") String four,
                                                  @Field("5") String five,
                                                  @Field("6") String six,
                                                  @Field("7") String seven,
                                                  @Field("8") String eight,
                                                  @Field("9") String nine,
                                                  @Field("10") String ten,
                                                  @Field("11") String eleven,
                                                  @Field("12") String twelve,
                                                  @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("healthcare/surat/simulator")
    Call<ResponseObject> SimulasiAnalisisKinerjaRSv2(@Header("Authorization") String authHeader,
                                                     @Field("token") String token,
                                                     @Field("type") String type,
                                                     @Field("1") String one,
                                                     @Field("2") String two,
                                                     @Field("3") String three,
                                                     @Field("4") String four,
                                                     @Field("5") String five,
                                                     @Field("6") String six,
                                                     @Field("7") String seven,
                                                     @Field("8") String eight,
                                                     @Field("9") String nine,
                                                     @Field("10") String ten,
                                                     @Field("11") String eleven,
                                                     @Field("12") String twelve,
                                                     @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseArrayObject> login(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("andro_token") String andro_token);

    @FormUrlEncoded
    @POST("login_nip")
    Call<ResponseArrayObject> login_nip(@Field("nip") String nip,
                                        @Field("password") String password,
                                        @Field("andro_token") String andro_token);

    @FormUrlEncoded
    @POST("search")
    Call<ResponseArrayObject> Search(@Field("search") String search,
                                        @Field("industry") String industry,
                                        @Field("token") String token);



    @FormUrlEncoded
    @POST("vote_design")
    Call<ResponseArrayObject> VoteDesign(@Field("token") String token,
                                    @Field("id_design_vote") String id_design_vote);

    @FormUrlEncoded
    @POST("daftar_design")
    Call<ResponseArrayObject> DaftarDesign(@Field("token") String token);

    @FormUrlEncoded
    @POST("check_vote")
    Call<ResponseString> CheckVote(@Field("token") String token);



    @FormUrlEncoded
    @POST("farming/benchmark")
    Call<ResponseArrayObject> BenchmarkFarming(@Field("token") String token);

    @FormUrlEncoded
    @POST("forget_pass")
    Call<ResponseArrayObject> ForgetPassword(@Field("email") String email);



    @FormUrlEncoded
    @POST("validasi_regist")
    Call<ResponseArrayObject> validasi_regist(@Field("token") String token);


    @FormUrlEncoded
    @POST("validasi_regist")
    Call<ResponseArrayObject> report(@Field("token") String token,
                                              @Field("tipe_post") String tipe_post,
                                              @Field("note") String note);

    @FormUrlEncoded
    @POST("berita")
    Call<ResponseArrayObject> Berita(@Field("token") String token,
                                     @Field("kategori") String kategori,
                                     @Field("page") String page);

    //Healthcare
    @FormUrlEncoded
    @POST("healthcare/business_refrence")
    Call<ResponseArrayObject> HealthCare_Business_refrence(@Field("token") String token,
                                                           @Field("jenis_br") String jenis_br);

    @FormUrlEncoded
    @POST("healthcare/business_refrence")
    Call<ResponseArrayObject> HealthCare_Business_refrence(@Field("token") String token,
                                                           @Field("regulasi") String regulasi,
                                                           @Field("jenis_br") String jenis_br);

    @FormUrlEncoded
    @POST("healthcare/list_of_probing")
    Call<ResponseArrayObject> HealthCare_List_of_Probing(@Field("token") String token);

    @FormUrlEncoded
    @POST("healthcare/key_of_success")
    Call<ResponseArrayObject> HealthCare_Key_Of_Success(@Field("token") String token);

    @FormUrlEncoded
    @POST("healthcare/video")
    Call<ResponseArrayObject> HealthCare_Compliance(@Field("token") String token);

    @FormUrlEncoded
    @POST("healthcare/alkes")
    Call<ResponseArrayObject> HealthCare_Hospital_Equipment(@Field("token") String token);

    @FormUrlEncoded
    @POST("daerah/provinsi")
    Call<ResponseArrayObject> Provinsi(@Field("token") String token);

    @FormUrlEncoded
    @POST("daerah/kab_kota")
    Call<ResponseArrayObject> Kota(@Field("token") String token,
                                   @Field("id_provinsi") String id_provinsi);

//    @FormUrlEncoded
//    @POST("healthcare/hospital")
//    Call<ResponseArrayObject> ListHospital(@Field("token") String token,
//                                           @Field("provinsi_rs") String provinsi_rs,
//                                           @Field("kab_kota_rs") String kab_kota_rs,
//                                           @Field("kelas_rs") String kelas_rs,
//                                           @Field("nama_rs") String nama_rs);

    @FormUrlEncoded
    @POST("healthcare/hospital")
    Call<ResponseObject> ListHospital(@Field("token") String token,
                                           @Field("provinsi_rs") String provinsi_rs,
                                           @Field("kab_kota_rs") String kab_kota_rs,
                                           @Field("kelas_rs") String kelas_rs,
                                           @Field("nama_rs") String nama_rs);


    //FMCG
    @FormUrlEncoded
    @POST("fmcg/video")
    Call<ResponseArrayObject> FMCG_Compliance(@Field("token") String token);

    @FormUrlEncoded
    @POST("fmcg/list_of_probing")
    Call<ResponseArrayObject> FMCG_List_of_Probing(@Field("token") String token,
                                                   @Field("kategori_probing") String kategori_probing);



    @FormUrlEncoded
    @POST("register")
    Call<ResponseArrayObject> Register(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("nip") String nip,
                                       @Field("no_telp") String no_telp,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("fmcg/business_refrence")
    Call<ResponseArrayObject> FMCG_Business_refrence(@Field("token") String token,
                                                           @Field("regulasi") String regulasi,
                                                           @Field("jenis_br") String jenis_br,
                                                           @Field("tipe") String tipe);

    //Forum
    @FormUrlEncoded
    @POST("delete_posting")
    Call<ResponseArrayObject> DeletePosting(@Field("token") String token,
                                            @Field("id_post") String id_post,
                                            @Field("tipe_post") String tipe_post);

    @FormUrlEncoded
    @POST("buat_percakapan_expert")
    Call<ResponseObject> BuatPercakapanExpert(@Field("token") String token,
                                               @Field("kategori") String kategori,
                                               @Field("isi") String isi,
                                               @Field("topik_percakapan") String topik_percakapan);

    @FormUrlEncoded
    @POST("daftar_percakapan_expert")
    Call<ResponseArrayObject> DaftarPercakapanExpert(@Field("token") String token,
                                          @Field("kategori") String kategori);

    @FormUrlEncoded
    @POST("report_post")
    Call<ResponseArrayObject> ReportPosting(@Field("token") String token,
                                            @Field("id_post") String id_post,
                                            @Field("tipe_post") String tipe_post,
                                            @Field("note") String note);


    @FormUrlEncoded
    @POST("berita_detail")
    Call<ResponseObject> BeritaDetail(@Field("token") String token,
                                            @Field("id_berita") String id_berita);

    @FormUrlEncoded
    @POST("daftar_posting")
    Call<ResponseArrayObject> Daftar_Postingan(@Field("token") String token,
                                               @Field("kategori") String kategori,
                                               @Field("page") String page,
                                               @Field("judul") String judul);
    @FormUrlEncoded
    @POST("profil")
    Call<ResponseObject> Profil(@Field("token") String token,
                                @Field("andro_token") String andro_token);

    @FormUrlEncoded
    @POST("poin")
    Call<ResponseObject> Poin(@Field("token") String token);

    @FormUrlEncoded
    @POST("check_quiz")
    Call<ResponseArrayObject> check_quiz(@Field("token") String token);

    @FormUrlEncoded
    @POST("quiz_harian")
    Call<ResponseQuiz> quiz_harian(@Field("token") String token);

    @FormUrlEncoded
    @POST("detail_posting")
    Call<ResponseObject> DetailPosting(@Field("token") String token,
                                       @Field("id_post") String id_post);

    @FormUrlEncoded
    @POST("update_divisi")
    Call<ResponseObject> UpdateDivisi(@Field("token") String token,
                                      @Field("id_divisi_mandiri") String id_divisi_mandiri,
                                      @Field("id_wilayah_mandiri") String id_wilayah_mandiri);

    @FormUrlEncoded
    @POST("update_user_reg")
    Call<ResponseObject> UpdateUserRegion(@Field("token") String token,
                                          @Field("tipe") String tipe,
                                          @Field("id_area") String id_area,
                                          @Field("cabang") String cabang);

    @FormUrlEncoded
    @POST("edit_profil")
    Call<ResponseObject> EditProfil(@Field("token") String token,
                                          @Field("nama_user") String nama_user,
                                          @Field("nip_user") String nip_user,
                                          @Field("no_telpon_user") String no_telpon_user);

    @FormUrlEncoded
    @POST("update_user_reg")
    Call<ResponseObject> UpdateUserKantor(@Field("token") String token,
                                          @Field("tipe") String tipe,
                                          @Field("id_group_pusat") String id_group_pusat,
                                          @Field("department") String department);

    @FormUrlEncoded
    @POST("jawab_quiz_harian")
    Call<ResponseObject> JawabQuizHarian(@Field("token") String token,
                                         @Field("id_quiz") String id_quiz,
                                         @Field("jawaban") String jawaban);

    @FormUrlEncoded
    @POST("hapus_img_post")
    Call<ResponseObject> DeleteGambarPost(@Field("token") String token,
                                       @Field("id_post_img") String id_post_img);

    @Multipart
    @POST("post_komen")
    Call<ResponseObject> PostKomen(@Part("token") RequestBody token,
                                   @Part("id_post") RequestBody id_post,
                                   @Part("isi_komen") RequestBody isi_komen,
                                   @Part MultipartBody.Part img_komen);

    @Multipart
    @POST("edit_komen")
    Call<ResponseObject> EditKomen(@Part("token") RequestBody token,
                                   @Part("id_post_komen") RequestBody id_post_komen,
                                   @Part("isi_komen") RequestBody isi_komen);

    @Multipart
    @POST("edit_komen")
    Call<ResponseObject> EditKomen(@Part("token") RequestBody token,
                                   @Part("id_post_komen") RequestBody id_post_komen,
                                   @Part("isi_komen") RequestBody isi_komen,
                                   @Part MultipartBody.Part img_komen);

    @Multipart
    @POST("post_komen")
    Call<ResponseObject> PostKomen(@Part("token") RequestBody token,
                                   @Part("id_post") RequestBody id_post,
                                   @Part("isi_komen") RequestBody isi_komen);

    @Multipart
    @POST("edit_komen_sub")
    Call<ResponseObject> EditSubKomen(@Part("token") RequestBody token,
                                      @Part("id_post_komen_sub") RequestBody id_post_komen_sub,
                                      @Part("isi_komen") RequestBody isi_komen,
                                      @Part MultipartBody.Part img_komen);

    @Multipart
    @POST("edit_komen_sub")
    Call<ResponseObject> EditSubKomen(@Part("token") RequestBody token,
                                      @Part("id_post_komen_sub") RequestBody id_post_komen_sub,
                                      @Part("isi_komen") RequestBody isi_komen);



    @Multipart
    @POST("post_komen_sub")
    Call<ResponseObject> PostSubKomen(@Part("token") RequestBody token,
                                      @Part("id_post") RequestBody id_post,
                                      @Part("isi_komen") RequestBody isi_komen,
                                      @Part("id_komen") RequestBody id_komen,
                                      @Part MultipartBody.Part img_komen);

    @Multipart
    @POST("post_komen_sub")
    Call<ResponseObject> PostSubKomen(@Part("token") RequestBody token,
                                      @Part("id_post") RequestBody id_post,
                                      @Part("isi_komen") RequestBody isi_komen,
                                      @Part("id_komen") RequestBody id_komen);


    //No Image
    @Multipart
    @POST("edit_post")
    Call<ResponseObject> EditForum(@Part("token") RequestBody token,
                                      @Part("id_post") RequestBody id_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen);

    @Multipart
    @POST("posting")
    Call<ResponseObject> PostingForum(@Part("token") RequestBody token,
                                      @Part("kategori_post") RequestBody kategori_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen);

    //1
    @Multipart
    @POST("tambah_img_post")
    Call<ResponseObject> TambahImagePost(@Part("token") RequestBody token,
                                      @Part("id_post") RequestBody id_post,
                                      @Part MultipartBody.Part img_post1);


    @Multipart
    @POST("posting")
    Call<ResponseObject> PostingForum(@Part("token") RequestBody token,
                                      @Part("kategori_post") RequestBody kategori_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen,
                                      @Part MultipartBody.Part img_post1,
                                      @Part("caption[]") RequestBody caption1);

    //2
    @Multipart
    @POST("posting")
    Call<ResponseObject> PostingForum(@Part("token") RequestBody token,
                                      @Part("kategori_post") RequestBody kategori_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen,
                                      @Part MultipartBody.Part img_post1,
                                      @Part("caption[]") RequestBody caption1,
                                      @Part MultipartBody.Part img_post2,
                                      @Part("caption[]") RequestBody caption2);

    //3
    @Multipart
    @POST("posting")
    Call<ResponseObject> PostingForum(@Part("token") RequestBody token,
                                      @Part("kategori_post") RequestBody kategori_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen,
                                      @Part MultipartBody.Part img_post1,
                                      @Part("caption[]") RequestBody caption1,
                                      @Part MultipartBody.Part img_post2,
                                      @Part("caption[]") RequestBody caption2,
                                      @Part MultipartBody.Part img_post3,
                                      @Part("caption[]") RequestBody caption3);


    //4
    @Multipart
    @POST("posting")
    Call<ResponseObject> PostingForum(@Part("token") RequestBody token,
                                      @Part("kategori_post") RequestBody kategori_post,
                                      @Part("judul_post") RequestBody judul_post,
                                      @Part("isi_post") RequestBody id_komen,
                                      @Part MultipartBody.Part img_post1,
                                      @Part("caption[]") RequestBody caption1,
                                      @Part MultipartBody.Part img_post2,
                                      @Part("caption[]") RequestBody caption2,
                                      @Part MultipartBody.Part img_post3,
                                      @Part("caption[]") RequestBody caption3,
                                      @Part MultipartBody.Part img_post4,
                                      @Part("caption[]") RequestBody caption4);


    @Multipart
    @POST("edit_foto")
    Call<ResponseObject> EditFoto(@Part("token") RequestBody token,
                                      @Part MultipartBody.Part img_profil);

    //GET
    @GET("divisi")
    Call<ResponseArrayObject> Divisi();

    @GET("wilayah")
    Call<ResponseArrayObject> Wilayah();


    //New API 1/3/2022
    @FormUrlEncoded
    @POST("direktorat")
    Call<ResponseArrayObject> Direktorat(@Field("token") String token);

    @FormUrlEncoded
    @POST("group")
    Call<ResponseArrayObject> Group(@Field("token") String token);

    @FormUrlEncoded
    @POST("region")
    Call<ResponseArrayObject> Region(@Field("token") String token);

    @FormUrlEncoded
    @POST("area")
    Call<ResponseArrayObject> Area(@Field("token") String token,
                                   @Field("id_region") String id_region);

    //Old API
    //FMCG
    @GET("ebook/sustainable_ebook?kuncifaba=FABAJakartaIndonesia2019kunci")
    Call<ResponseArrayObject> SustainableFMCG(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("fmcg/perusahaan")
    Call<Perusahaan> DataPerusahaan(@Header("Authorization") String authHeader,
                                    @Field("kuncifaba") String kuncifaba,
                                    @Field("nama") String nama,
                                    @Field("tipe") String tipe,
                                    @Field("kategori") String kategori,
                                    @Field("page") String page,
                                    @Field("tbk") String tbk,
                                    @Field("id_provinsi") String id_provinsi,
                                    @Field("id_kab_kota") String id_kab_kota);

    @FormUrlEncoded
    @POST("fmcg/perusahaan")
    Call<Perusahaan> DataPerusahaan(@Header("Authorization") String authHeader,
                                    @Field("kuncifaba") String kuncifaba,
                                    @Field("id_perusahaan") String id_perusahaan);

    @FormUrlEncoded
    @POST("fmcg/asosiasi")
    Call<DataModel> DetailAssosiationFMCG(@Header("Authorization") String authHeader,
                                          @Field("kuncifaba") String kuncifaba,
                                          @Field("id_asosiasi") String id_asosiasi);

    @FormUrlEncoded
    @POST("fmcg/asosiasi")
    Call<Probing> AssosiationFMCG(@Header("Authorization") String authHeader,
                                  @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("fmcg/key_of_success")
    Call<Probing> KosFMCG(@Header("Authorization") String authHeader,
                          @Field("token") String token,
                          @Field("industri") String industri);

    @FormUrlEncoded
    @POST("fmcg/param")
    Call<RumusKMK> KMK(@Header("Authorization") String authHeader,
                       @Field("kuncifaba") String kuncifaba,
                       @Field("nama_param") String nama_param,
                       @Field("kategori") String kategori);

    @FormUrlEncoded
    @POST("fmcg/param")
    Call<ResponseKMK> KMKV2(@Header("Authorization") String authHeader,
                            @Field("token") String token,
                            @Field("nama_param") String nama_param,
                            @Field("kategori") String kategori);

    @FormUrlEncoded
    @POST("fmcg/permintaan/potensial_market")
    Call<ResponseArrayObject> PotensialMarket(@Header("Authorization") String authHeader,
                                        @Field("kuncifaba") String kuncifaba,
                                        @Field("id_provinsi") String id_provinsi,
                                        @Field("tipe") String tipe);

    @FormUrlEncoded
    @POST("fivec")
    Call<ResponseArrayObject> fivec(@Header("Authorization") String authHeader,
                              @Field("kuncifaba") String kuncifaba,
                              @Field("kategori") String kategori,
                              @Field("pernyataan") String pernyataan,
                              @Field("tipe") String tipe);

    @FormUrlEncoded
    @POST("fmcg/param")
    Call<ResponseArrayObject> KMK2(@Header("Authorization") String authHeader,
                             @Field("kuncifaba") String kuncifaba,
                             @Field("kmk") String kmk,
                             @Field("kategori") String kategori);

    //Mining
    @FormUrlEncoded
    @POST("mining/video")
    Call<ResponseArrayObject> Mining_compliance(@Field("token") String token);


    @FormUrlEncoded
    @POST("mandiri_update")
    Call<ResponseArrayObject> MandiriUpdate(@Field("token") String token,
                                            @Field("kategori") String kategori,
                                            @Field("page") String page);

    @FormUrlEncoded
    @POST("mandiri_update/detail")
    Call<ResponseDataModel> DetailMandiriUpdate(@Field("token") String token,
                                                @Field("id_mandiri_update") String id_mandiri_update);

    @FormUrlEncoded
    @POST("mining/list_of_probing")
    Call<ResponseArrayObject> Mining_List_of_Probing(@Field("token") String token);

    @FormUrlEncoded
    @POST("farming/list_of_probing")
    Call<ResponseArrayObject> Farming_List_of_Probing(@Field("token") String token);

    @FormUrlEncoded
    @POST("mining/outlook")
    Call<ResponseArrayObject> MiningOutlook(@Field("token") String token,
                                             @Field("jenis_outlook") String jenis_outlook);

    @FormUrlEncoded
    @POST("farming/perusahaan")
    Call<ResponseArrayObject> FarmingPerusahaan(@Field("token") String token,
                                            @Field("kategori_perusahaan") String kategori_perusahaan);

    @FormUrlEncoded
    @POST("hospital/detail")
    Call<ResponseArrayObject> detailHospital(@Header("Authorization") String authHeader,
                                       @Field("kode_rs") String kode_rs,
                                       @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("hospital/list")
    Call<ResponseArrayObject> listHospital(@Header("Authorization") String authHeader,
                                     @Field("kab_kota_rs") String kab_kota_rs,
                                     @Field("provinsi_rs") String provinsi_rs,
                                     @Field("nama_rs") String nama_rs,
                                     @Field("kelas_rs") String kelas_rs,
                                     @Field("kuncifaba") String kuncifaba);

    @FormUrlEncoded
    @POST("farming/video")
    Call<ResponseArrayObject> Farming_Complpiance(@Field("token") String token);

    @FormUrlEncoded
    @POST("farming/outlook")
    Call<ResponseArrayObject> FarmingOutlook(@Field("token") String token,
                                            @Field("jenis_outlook") String jenis_outlook);

    @FormUrlEncoded
    @POST("oil_and_gas/video")
    Call<ResponseArrayObject> OilAndGas_Complpiance(@Field("token") String token);

    @FormUrlEncoded
    @POST("oil_and_gas/list_of_probing")
    Call<ResponseArrayObject> OilAndGas_List_of_Probing(@Field("token") String token);

    @FormUrlEncoded
    @POST("contractor/video")
    Call<ResponseArrayObject> Contractor_compliance(@Field("token") String token);

    @FormUrlEncoded
    @POST("contractor/perusahaan")
    Call<ResponseArrayObject> PerusahaanContractor(@Field("token") String token,
                                             @Field("jenis") String jenis);

    @FormUrlEncoded
    @POST("log")
    Call<ResponseArrayObject> Log(@Field("token") String token,
                                                   @Field("id_log_menu") String id_log_menu);

    @FormUrlEncoded
    @POST("change_pass")
    Call<ResponseArrayObject> ChangePassword(@Field("token") String token,
                                             @Field("pass_lama") String pass_lama,
                                             @Field("pass_baru") String pass_baru,
                                             @Field("pass_conf") String pass_conf);


    @POST("version")
    Call<ResponseObject> Version();

    @FormUrlEncoded
    @POST("hadiah")
    Call<ResponseArrayObject> DaftarHadiah(@Field("token") String token);

    @FormUrlEncoded
    @POST("penukaran")
    Call<ResponseArrayObject> PenukaranHadiah(@Field("token") String token,
                                              @Field("id_poin_hadiah") String id_poin_hadiah);

    @FormUrlEncoded
    @POST("history_poin")
    Call<ResponseArrayObject> HistoryPoin(@Field("token") String token);

    @FormUrlEncoded
    @POST("history_penukaran")
    Call<ResponseArrayObject> HistoryPenukaran(@Field("token") String token);

    @FormUrlEncoded
    @POST("notif_baru")
    Call<ResponseObject> NotifBaru(@Field("token") String token);

    @FormUrlEncoded
    @POST("notif")
    Call<ResponseArrayObject> DaftarNotif(@Field("token") String token);


    @FormUrlEncoded
    @POST("lihat_notif")
    Call<ResponseArrayObject> LihatNotif(@Field("token") String token);

    @FormUrlEncoded
    @POST("contractor/perusahaan_book")
    Call<ResponseArrayObject> BukuPerusahaan(@Field("token") String token);

    @FormUrlEncoded
    @POST("contractor/outlook")
    Call<ResponseArrayObject> OutlookContractor(@Field("token") String token,
                                             @Field("jenis_outlook") String jenis_outlook);

    @FormUrlEncoded
    @POST("oil_and_gas/outlook")
    Call<ResponseArrayObject> OilAndGasContractor(@Field("token") String token,
                                                @Field("jenis_outlook") String jenis_outlook);
}
