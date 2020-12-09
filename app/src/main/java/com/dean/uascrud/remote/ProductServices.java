package com.dean.uascrud.remote;

import com.dean.uascrud.model.PersonItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductServices {

    @GET("person/get/")
    Call<List<PersonItem>> getProduct();

    @FormUrlEncoded
    @POST("person/add/")
    Call<PersonItem> addProduct(@Field("name") String name,
                                @Field("class") String clas,
                                @Field("lesson") String lesson,
                                @Field("date") String date,
                                @Field("ket") String ket);

    @FormUrlEncoded
    @PUT("person/update/")
    Call<PersonItem> updateProduct(@Field("id") int id,
                                   @Field("name") String name,
                                   @Field("class") String clas,
                                   @Field("lesson") String lesson,
                                   @Field("date") String date,
                                   @Field("ket") String ket);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = ("person/delete/"), hasBody = true)
    Call<PersonItem> deleteProduct(@Field("id") int id);
}
