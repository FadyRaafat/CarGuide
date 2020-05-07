package com.fady.carguide.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fady.carguide.datamodel.models.CarsResponse;
import com.fady.carguide.datamodel.repository.CarsRepository;

public class CarsViewModel extends ViewModel {
    private CarsRepository carsRepository;

    public CarsViewModel() {
        carsRepository = new CarsRepository();
    }

    public MutableLiveData<CarsResponse> getCarsList(Integer pageNumber) {
        return carsRepository.getCountriesList(pageNumber);

    }


}
