package com.proyecto.main.api.service;

import com.proyecto.main.api.entities.Dolar;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class PrecioDolar {
    private static final String apiURL = "https://api-dolar-argentina.herokuapp.com/";
    private Retrofit retrofit;

    public PrecioDolar() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Dolar datosDolar() throws IOException {
        DolarService service = this.retrofit.create(DolarService.class); // <- OBJETO ANONIMO

        Call<Dolar> request = service.datos();

        Response<Dolar> response = request.execute();
        response.code();// <- 200

        return response.body();
    }
}
