package com.taniafontcuberta.basketball.controller.managers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taniafontcuberta.basketball.controller.services.AtletaService;
import com.taniafontcuberta.basketball.model.Atleta;
import com.taniafontcuberta.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AtletaManager {
    private static AtletaManager ourInstance;
    private List<Atleta> atletas;
    private Retrofit retrofit;
    private AtletaService atletaService;

    private AtletaManager() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        atletaService = retrofit.create(AtletaService.class);
    }

    public static AtletaManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AtletaManager();
        }

        return ourInstance;
    }


    public synchronized void getAllAthletes(final AtletaCallback atletaCallback) {
        Call<List<Atleta>> call = atletaService.getAllAthletes(UserLoginManager.getInstance().getBearerToken());

        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (response.isSuccess()) {
                    atletaCallback.onSuccess(atletas);
                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getAllAthletes()->ERROR: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    public Atleta getAthlete(String id) {
        for (Atleta atleta : atletas) {
            if (id.equals(atleta.getId().toString())) {
                return atleta;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createAthlete(final AtletaCallback atletaCallback, Atleta atleta) {
        Call<Atleta> call = atletaService.createAthlete(UserLoginManager.getInstance().getBearerToken(), atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //playerCallback.onSuccess1(apuestas1x2);
                    Log.e("Atleta->", "createAthlete: OK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AtletaManager->", "createAthlete: " + t);
                atletaCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE Athlete */
    public synchronized void updateAthlete(final AtletaCallback atletaCallback, Atleta atleta) {
        Call <Atleta> call = atletaService.updateAthlete(UserLoginManager.getInstance().getBearerToken() ,atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "updateAthlete: OOK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("PlayerManager->", "updatePlayer: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* DELETE - DELETE PLAYER */
    public synchronized void deleteAthlete(final AtletaCallback atletaCallback, Long id) {
        Call <Void> call = atletaService.deleteAthlete(UserLoginManager.getInstance().getBearerToken() ,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Player->", "Deleted: OK");

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("AtletaManager->", "deleteAthlete: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

}
