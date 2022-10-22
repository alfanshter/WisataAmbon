package com.sarah.wisata.webservice;



import com.sarah.wisata.model.DataWisataResponse;
import com.sarah.wisata.model.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data_wisata_kategori")
    Call<DataWisataResponse> data_wisata_kategori(
            @Query("kategori") String kategori
    );

    @GET("data_event")
    Call<EventResponse> data_event();
}