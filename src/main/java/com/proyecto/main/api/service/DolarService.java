package com.proyecto.main.api.service;

import com.proyecto.main.api.entities.Dolar;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DolarService {
    @GET("api/dolarblue")
    Call<Dolar> datos();
}
