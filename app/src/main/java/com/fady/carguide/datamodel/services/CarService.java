package com.fady.carguide.datamodel.services;


import com.fady.carguide.datamodel.models.CarsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarService {

    @GET("cars")
    Call<CarsResponse> getCarsList(@Query("page") Integer pageNumber);

}
