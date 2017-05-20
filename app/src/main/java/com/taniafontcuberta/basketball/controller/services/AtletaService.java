package com.taniafontcuberta.basketball.controller.services;


import com.taniafontcuberta.basketball.model.Atleta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtletaService {
    @GET("/api/atletas")
    Call<List<Atleta>> getAllAthletes(
            @Header("Authorization") String Authorization
    );

    @POST("api/atletas") // Se tiene que cambiar en un interfaz propia
    Call<Atleta> createAthlete(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);


    @PUT("api/atletas")
    Call<Atleta> updateAthlete(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);

    @DELETE("api/atletas/{id}")
    Call<Void> deleteAthlete(
            @Header("Authorization") String Authorization,
            @Path("id") Long id);


}
