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
    @POST("register")
    Call<ResponseArrayObject> Register(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("nip") String nip,
                                       @Field("no_telp") String no_telp,
                                       @Field("divisi") String divisi,
                                       @Field("password") String password);

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
