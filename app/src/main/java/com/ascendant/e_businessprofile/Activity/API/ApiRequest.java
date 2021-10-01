package com.ascendant.e_businessprofile.Activity.API;

import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseArrayObject> login(@Field("email") String email,
                                    @Field("password") String password);

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
                                       @Field("divisi") String divisi,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("fmcg/business_refrence")
    Call<ResponseArrayObject> FMCG_Business_refrence(@Field("token") String token,
                                                           @Field("regulasi") String regulasi,
                                                           @Field("jenis_br") String jenis_br,
                                                           @Field("tipe") String tipe);

    @FormUrlEncoded
    @POST("profil")
    Call<ResponseObject> Profil(@Field("token") String token);

    @FormUrlEncoded
    @POST("poin")
    Call<ResponseObject> Poin(@Field("token") String token);

    //GET
    @GET("divisi")
    Call<ResponseArrayObject> Divisi();


}
