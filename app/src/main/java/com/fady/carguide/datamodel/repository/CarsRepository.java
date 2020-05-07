package com.fady.carguide.datamodel.repository;

import androidx.lifecycle.MutableLiveData;

import com.fady.carguide.datamodel.models.CarsResponse;
import com.fady.carguide.datamodel.services.CarService;
import com.fady.carguide.datamodel.services.ServiceBuilder;
import com.fady.carguide.utilis.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsRepository {

    public MutableLiveData<CarsResponse> getCountriesList(Integer pageNumber) {
         MutableLiveData<CarsResponse> carsResponseMutableLiveData = new MutableLiveData<>();

        CarService carService = ServiceBuilder.buildService(CarService.class);
        Call<CarsResponse> call = carService.getCarsList(pageNumber);
        call.enqueue(new Callback<CarsResponse>() {
            @Override
            public void onResponse(Call<CarsResponse> request, Response<CarsResponse> response) {
                carsResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CarsResponse> request, Throwable t) {

            }
        });
        return carsResponseMutableLiveData;

    }

}
